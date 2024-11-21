package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.IssueModel;
import com.erneto13.sgfa_backend.repository.IIssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesService implements IIssuesService {

    @Autowired
    private IIssuesRepository iIssuesRepository;

    @Override
    public List<IssueModel> getAllIssues() {
        return iIssuesRepository.findAll();
    }

    @Override
    public List<IssueModel> getIssueByType(String type) {
        return iIssuesRepository.findByIssueType(type);
    }

    @Override
    public List<IssueModel> getIssueByStatus(String status) {
        return iIssuesRepository.findByStatus(status);
    }

    @Override
    public List<IssueModel> getIssueByTypePerUser(String type, String user) {
        return iIssuesRepository.findByIssueTypePerUser(type, user);
    }

    @Override
    public List<IssueModel> getClosedIssuesByUser(String user) {
        return iIssuesRepository.findClosedIssuesByUser(user);
    }

    @Override
    public int pushIssue(IssueModel issue) {
        return iIssuesRepository.pushIssue(issue);
    }

    @Override
    public int updateStatus(int id, String status) {
        return iIssuesRepository.updateIssueStatus(id, status);
    }

    @Override
    public int resolveIssue(IssueModel issue) {
        return iIssuesRepository.updateIssueResolution(issue);
    }

    @Override
    public IssueModel getIssueById(int id) {
        return iIssuesRepository.findById(id);
    }
}