package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.RemindersModel;
import com.erneto13.sgfa_backend.service.IRemindersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/reminders")
public class RemindersController {

    @Autowired
    private IRemindersService remindersService;

    @GetMapping("reminders-list")
    public ResponseEntity<List<RemindersModel>> getAllReminders() {
        return new ResponseEntity<>(remindersService.getAllReminders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemindersModel> getReminderById(@PathVariable Long id) {
        return new ResponseEntity<>(remindersService.getReminderById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RemindersModel> createReminder(@RequestBody RemindersModel reminder) {
        return new ResponseEntity<>(remindersService.createReminder(reminder), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        remindersService.deleteReminder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
