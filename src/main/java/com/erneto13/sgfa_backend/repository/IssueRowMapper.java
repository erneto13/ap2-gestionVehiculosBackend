package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.IssueModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class IssueRowMapper implements RowMapper<IssueModel> {
    @Override
    public IssueModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        IssueModel issue = new IssueModel();
        issue.setIdissues(rs.getInt("idissues"));
        issue.setTitle(rs.getString("title"));
        issue.setDescription(rs.getString("description"));
        issue.setIssue_type(rs.getString("issue_type"));
        issue.setStatus(rs.getString("status"));

        try {
            String evidenceJson = rs.getString("evidence");
            if (evidenceJson != null) {
                ObjectMapper mapper = new ObjectMapper();
                issue.setEvidence(mapper.readValue(evidenceJson, new TypeReference<List<String>>() {
                }));
            }
        } catch (Exception e) {
            throw new SQLException("Error parsing evidence JSON", e);
        }

        issue.setComments(rs.getString("comments"));
        issue.setReportedBy(rs.getString("reported_by"));

        Timestamp reportDate = rs.getTimestamp("report_date");
        if (reportDate != null) {
            issue.setReportDate(reportDate.toLocalDateTime());
        }

        issue.setResolvedBy(rs.getString("resolved_by"));

        Timestamp resolvedDate = rs.getTimestamp("resolved_date");
        if (resolvedDate != null) {
            issue.setResolvedDate(resolvedDate.toLocalDateTime());
        }

        return issue;
    }
}
