package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.IssueModel;

import java.util.List;

public interface IIssuesRepository {
    public List<IssueModel> findAll();
    public List<IssueModel> findByIssueType(String type);
    public List<IssueModel> findByStatus(String status);
    public List<IssueModel> findByIssueTypePerUser(String type, String user);
    public List<IssueModel> findClosedIssuesByUser(String user);
    int pushIssue(IssueModel issue);
    int updateIssueStatus(int id, String status);
    int updateIssueResolution(IssueModel issue);
    IssueModel findById(int id);
}