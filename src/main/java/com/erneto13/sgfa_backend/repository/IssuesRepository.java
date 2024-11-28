package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.IssueModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssuesRepository implements IIssuesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<IssueModel> findAll() {
        String query = """
                SELECT 
                    idissues, title, description, issue_type, status,
                    evidence, comments, reported_by, report_date, resolved_by, resolved_date 
                FROM issues
                """;
        return jdbcTemplate.query(query, new IssueRowMapper());
    }

    @Override
    public List<IssueModel> findByIssueType(String type) {
        String query = "SELECT * FROM issues WHERE issue_type = ? AND status != 'closed'";
        return jdbcTemplate.query(query, new IssueRowMapper(), type);
    }

    @Override
    public List<IssueModel> findByStatus(String status) {
        String query = "SELECT * FROM issues WHERE status = ?";
        return jdbcTemplate.query(query, new IssueRowMapper(), status);
    }

    @Override
    public List<IssueModel> findByIssueTypePerUser(String type, String user) {
        String query = "SELECT * FROM issues WHERE issue_type = ? AND reported_by = ? AND (status = 'pending' OR status = 'in_progress')";
        return jdbcTemplate.query(query, new IssueRowMapper(), type, user);
    }

    @Override
    public List<IssueModel> findClosedIssuesByUser(String user) {
        String query = "SELECT * FROM issues WHERE reported_by = ? AND status = 'closed'";
        return jdbcTemplate.query(query, new IssueRowMapper(), user);
    }

    @Override
    public int pushIssue(IssueModel issue) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String evidenceJson = objectMapper.writeValueAsString(issue.getEvidence()); // Convierte la lista en JSON
            System.out.println("Evidence JSON: " + evidenceJson);
            String query = """
                        INSERT INTO issues (
                            title, description, issue_type, status,
                            evidence, comments, reported_by,
                            report_date, resolved_by, resolved_date
                        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            return jdbcTemplate.update(query,
                    issue.getTitle(),
                    issue.getDescription(),
                    issue.getIssue_type(),
                    issue.getStatus(),
                    evidenceJson, // Usamos el JSON convertido aquí
                    issue.getComments(),
                    issue.getReportedBy(),
                    issue.getReportDate(),
                    issue.getResolvedBy(),
                    issue.getResolvedDate()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateIssueStatus(int id, String status) {
        String sql = "UPDATE issues SET status = ? WHERE idissues = ?";
        return jdbcTemplate.update(sql, status, id);
    }

    @Override
    public int updateIssueResolution(IssueModel issue) {
        String sql = "UPDATE issues SET status = ?, comments = ?, resolved_by = ?, resolved_date = ? WHERE idissues = ?";
        return jdbcTemplate.update(sql, issue.getStatus(), issue.getComments(), issue.getResolvedBy(), issue.getResolvedDate(), issue.getIdissues());
    }

    @Override
    public IssueModel findById(int id) {
        String sql = "SELECT * FROM issues WHERE idissues = ?";
        return jdbcTemplate.queryForObject(sql, new IssueRowMapper(), id);
    }
}