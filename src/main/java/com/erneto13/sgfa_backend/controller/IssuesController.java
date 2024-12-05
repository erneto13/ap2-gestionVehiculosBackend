package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.IssueModel;
import com.erneto13.sgfa_backend.service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/issues")
public class IssuesController {

    @Autowired
    private IssuesService issuesService;

    @GetMapping
    public ResponseEntity<List<IssueModel>> list() {
        return ResponseEntity.ok(issuesService.getAllIssues());
    }

    @GetMapping("/type/{issueType}")
    public ResponseEntity<List<IssueModel>> getIssuesByType(@PathVariable String issueType) {
        return ResponseEntity.ok(issuesService.getIssueByType(issueType));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<IssueModel>> getIssuesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(issuesService.getIssueByStatus(status));
    }

    @GetMapping("/type/{issueType}/user/{user}")
    public ResponseEntity<List<IssueModel>> getIssuesByTypeAndUser(@PathVariable String issueType, @PathVariable String user) {
        List<IssueModel> issues = issuesService.getIssueByTypePerUser(issueType, user);
        return issues.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(issues);
    }

    @GetMapping("/closed/user/{user}")
    public ResponseEntity<List<IssueModel>> getClosedIssuesByUser(@PathVariable String user) {
        List<IssueModel> issues = issuesService.getClosedIssuesByUser(user);
        return issues.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(issues);
    }

    @PostMapping
    public ResponseEntity<?> createIssue(@RequestBody IssueModel issue) {
        try {
            IssueModel createdIssue = issuesService.pushIssue(issue);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIssue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating issue: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateIssueStatus(@PathVariable Integer id, @RequestBody Map<String, String> statusMap) {
        String status = statusMap.get("status");
        try {
            IssueModel updatedIssue = issuesService.updateStatus(id, status);
            return ResponseEntity.ok(updatedIssue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating issue status: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<?> resolveIssue(@PathVariable Integer id, @RequestBody IssueModel issueUpdate) {
        try {
            IssueModel existingIssue = issuesService.getIssueById(id);
            if (existingIssue == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue not found");
            }

            if (issueUpdate.getStatus() != null) {
                existingIssue.setStatus(issueUpdate.getStatus());
            }
            if (issueUpdate.getResolvedBy() != null) {
                existingIssue.setResolvedBy(issueUpdate.getResolvedBy());
            }
            if (issueUpdate.getResolvedDate() != null) {
                existingIssue.setResolvedDate(issueUpdate.getResolvedDate());
            }
            if (issueUpdate.getComments() != null) {
                existingIssue.setComments(issueUpdate.getComments());
            }

            IssueModel resolvedIssue = issuesService.resolveIssue(existingIssue);
            return ResponseEntity.ok(resolvedIssue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resolving issue: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIssueById(@PathVariable Integer id) {
        try {
            IssueModel issue = issuesService.getIssueById(id);
            return ResponseEntity.ok(issue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue not found");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable int id) {
        issuesService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }
}