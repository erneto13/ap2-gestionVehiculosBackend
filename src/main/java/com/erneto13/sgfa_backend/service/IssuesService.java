package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.IssuesModel;
import com.erneto13.sgfa_backend.repository.IIssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesService implements IIssuesService {

    @Autowired
    private IIssuesRepository iIssuesRepository;

    @Override
    public List<IssuesModel> getAllIssues() {
        List<IssuesModel> list;
        try {
            list = iIssuesRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    @Override
    public List<IssuesModel> getIssueByType(String type) {
        List<IssuesModel> list;
        try {
            list = iIssuesRepository.findByIssueType(type);
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
}
