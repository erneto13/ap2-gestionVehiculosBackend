package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.dto.IssueUpdateDTO;
import com.erneto13.sgfa_backend.model.IssueModel;
import com.erneto13.sgfa_backend.service.IIssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1")
public class IssuesController {

    @Autowired
    IIssuesService iIssueService;

    @GetMapping("/issues-list")
    public ResponseEntity<?> list() {
        List<IssueModel> issues = this.iIssueService.getAllIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/issues-list/type/{issue_type}")
    public ResponseEntity<?> getIssueByType(@PathVariable String issue_type) {
        List<IssueModel> issues = this.iIssueService.getIssueByType(issue_type);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/issues-list/status/{status}")
    public ResponseEntity<?> getIssueByStatus(@PathVariable String status) {
        List<IssueModel> issues = this.iIssueService.getIssueByStatus(status);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/issues-list/type/{issue_type}/user/{user}")
    public ResponseEntity<?> getIssueByTypeAndUser(@PathVariable String issue_type, @PathVariable String user) {
        List<IssueModel> issues = this.iIssueService.getIssueByTypePerUser(issue_type, user);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/issues-list/closed/user/{user}")
    public ResponseEntity<?> getClosedIssuesByUser(@PathVariable String user) {
        List<IssueModel> issues = this.iIssueService.getClosedIssuesByUser(user);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @PostMapping("/issues-list/pull-issue/")
    public ResponseEntity<?> createIssue(@RequestBody IssueModel issue) {
        try {
            int result = iIssueService.pushIssue(issue);
            if (result > 0) {
                Map<String, String> response = new HashMap<>();
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create issue");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating issue: " + e.getMessage());
        }
    }

    @PutMapping("/issues-list/update-status/{id}")
    public ResponseEntity<?> updateIssueStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        String status = statusMap.get("status");
        try {
            int result = iIssueService.updateStatus(Math.toIntExact(id), status);
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.OK).body("Issue status updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update issue status");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating issue status: " + e.getMessage());
        }
    }

    @PatchMapping("/issues-list/resolve/{id}")
    public ResponseEntity<?> resolveIssue(
            @PathVariable("id") Integer id,
            @RequestBody IssueUpdateDTO issueUpdate) {

        IssueModel existingIssue = iIssueService.getIssueById(id);
        if (existingIssue == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue no encontrado");
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

        int result = iIssueService.resolveIssue(existingIssue);
        if (result > 0) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Issue actualizado correctamente");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el issue");
        }
    }

}