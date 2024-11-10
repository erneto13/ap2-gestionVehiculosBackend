package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.IssuesModel;
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
    public List<IssuesModel> findAll() {
        String query = "SELECT * FROM issues";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(IssuesModel.class));
    }

    @Override
    public List<IssuesModel> findByIssueType(String type) {
        String query = "SELECT * FROM issues WHERE issue_type = ?";
        return jdbcTemplate.query(query, new Object[]{type}, BeanPropertyRowMapper.newInstance(IssuesModel.class));
    }

    @Override
    public List<IssuesModel> findByStatus(String status) {
        String query = "SELECT * FROM issues WHERE status = ?";
        return jdbcTemplate.query(query, new Object[]{status}, BeanPropertyRowMapper.newInstance(IssuesModel.class));
    }
}
