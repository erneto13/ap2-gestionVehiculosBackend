package com.erneto13.sgfa_backend.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssuesModel {
    Integer idissues;
    String title;
    String description;
    String issue_type;

    String status;
    String evidence;
    String comments;
    LocalDateTime reportDate = LocalDateTime.now();
    String resolvedBy;
    LocalDateTime resolvedDate;
}
