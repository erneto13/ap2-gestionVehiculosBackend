package com.erneto13.sgfa_backend.repository;


import com.erneto13.sgfa_backend.model.IssueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepository extends JpaRepository<IssueModel, Integer> {
    List<IssueModel> findByIssueType(String issueType);
    List<IssueModel> findByStatus(String status);
    List<IssueModel> findByIssueTypeAndReportedByAndStatusIn(String issueType, String reportedBy, List<String> statuses);
    List<IssueModel> findByReportedByAndStatus(String reportedBy, String status);
}

