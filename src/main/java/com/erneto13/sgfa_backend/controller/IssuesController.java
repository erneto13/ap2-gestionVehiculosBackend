package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.ContactsModel;
import com.erneto13.sgfa_backend.model.IssuesModel;
import com.erneto13.sgfa_backend.service.IIssuesService;
import com.erneto13.sgfa_backend.service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1")
public class IssuesController {

    @Autowired
    IIssuesService iIssueService;

    // Get all issues
    @GetMapping("/issues-list")
    public ResponseEntity<?> list() {
        List<IssuesModel> issues = this.iIssueService.getAllIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/issues-list/{issue_type}")
    public ResponseEntity<?> getIssueByType(@PathVariable String issue_type) {
        List<IssuesModel> issues = this.iIssueService.getIssueByType(issue_type);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/issues-list/status/{status}")
    public ResponseEntity<?> getIssueByStatus(@PathVariable String status) {
        List<IssuesModel> issues = this.iIssueService.getIssueByStatus(status);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
}
