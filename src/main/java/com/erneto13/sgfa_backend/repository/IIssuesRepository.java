package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.IssuesModel;

import java.util.List;

public interface IIssuesRepository {
    public List<IssuesModel> findAll();
    public List<IssuesModel> findByIssueType(String type);
    public List<IssuesModel> findByStatus(String status);
}
