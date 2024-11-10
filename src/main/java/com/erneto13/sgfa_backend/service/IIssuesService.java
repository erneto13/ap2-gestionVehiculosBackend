package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.IssuesModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IIssuesService {
    public List<IssuesModel> getAllIssues();
    public List<IssuesModel> getIssueByType(String type);
    public List<IssuesModel> getIssueByStatus(String status);
}
