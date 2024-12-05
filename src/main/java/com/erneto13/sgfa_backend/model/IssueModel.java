package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "issues")
public class IssueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issues_id;

    private String title;
    private String description;

    @Column(name = "issue_type")
    private String issueType;

    private String status;

    @ElementCollection
    @CollectionTable(name = "issue_evidence", joinColumns = @JoinColumn(name = "issue_id"))
    @Column(name = "evidence")
    private List<String> evidence;

    private String comments;

    @Column(name = "reported_by")
    private String reportedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "report_date")
    private LocalDateTime reportDate;

    @Column(name = "resolved_by")
    private String resolvedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "resolved_date")
    private LocalDateTime resolvedDate;
}
