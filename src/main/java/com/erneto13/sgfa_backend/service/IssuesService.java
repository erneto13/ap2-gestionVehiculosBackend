package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.IssueModel;
import com.erneto13.sgfa_backend.repository.IssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuesService {

    @Autowired
    private IssuesRepository issuesRepository;

    public List<IssueModel> getAllIssues() {
        return issuesRepository.findAll();
    }

    public List<IssueModel> getIssueByType(String type) {
        return issuesRepository.findByIssueType(type).stream()
                .filter(issue -> issue.getStatus().equals("PENDING") ||
                        issue.getStatus().equals("IN_PROGRESS"))
                .collect(Collectors.toList());
    }

    public List<IssueModel> getIssueByStatus(String status) {
        return issuesRepository.findByStatus(status);
    }

    public List<IssueModel> getIssueByTypePerUser(String type, String user) {
        return issuesRepository.findByIssueTypeAndReportedByAndStatusIn(type, user, List.of("pending", "in_progress"));
    }

    public List<IssueModel> getClosedIssuesByUser(String user) {
        return issuesRepository.findByReportedByAndStatus(user, "closed");
    }

    public IssueModel pushIssue(IssueModel issue) {
        return issuesRepository.save(issue);
    }

    public IssueModel updateStatus(int id, String status) {
        IssueModel issue = issuesRepository.findById(id).orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setStatus(status);
        return issuesRepository.save(issue);
    }

    public IssueModel resolveIssue(IssueModel issue) {
        return issuesRepository.save(issue);
    }

    public void deleteIssue(int id) {
        issuesRepository.deleteById(id);
    }

    public IssueModel getIssueById(int id) {
        return issuesRepository.findById(id).orElseThrow(() -> new RuntimeException("Issue not found"));
    }
}
