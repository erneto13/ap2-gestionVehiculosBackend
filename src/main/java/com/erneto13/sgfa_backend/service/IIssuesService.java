package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.IssueModel;

import java.util.List;

public interface IIssuesService {
    public List<IssueModel> getAllIssues();
    public List<IssueModel> getIssueByType(String type);
    public List<IssueModel> getIssueByStatus(String status);
    public List<IssueModel> getIssueByTypePerUser(String type, String user);
    public List<IssueModel> getClosedIssuesByUser(String user);
    public int pushIssue(IssueModel issue);
    public int updateStatus(int id, String status);
    int resolveIssue(IssueModel issue);
    IssueModel getIssueById(int id);
}