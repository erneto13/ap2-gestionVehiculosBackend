package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IssueModel {
    Integer idissues;
    String title;
    String description;
    String issue_type;

    String status;
    List<String> evidence;
    String comments;
    String reportedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime reportDate;

    String resolvedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime resolvedDate;
}
