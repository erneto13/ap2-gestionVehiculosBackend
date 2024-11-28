package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.RemindersModel;

import java.util.List;

public interface IRemindersService {
    List<RemindersModel> getAllReminders();
    RemindersModel getReminderById(Long id);
    RemindersModel createReminder(RemindersModel reminder);
    void deleteReminder(Long id);
}
