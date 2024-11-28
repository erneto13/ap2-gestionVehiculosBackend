package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.RemindersModel;
import com.erneto13.sgfa_backend.repository.IUtilsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindersService implements IRemindersService {

    @Autowired
    private IUtilsRepository remindersRepository;

    @Override
    public List<RemindersModel> getAllReminders() {
        return remindersRepository.findAll();
    }

    @Override
    public RemindersModel getReminderById(Long id) {
        return remindersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reminder not found with id: " + id));
    }

    @Override
    public RemindersModel createReminder(RemindersModel reminder) {
        return remindersRepository.save(reminder);
    }

    @Override
    public void deleteReminder(Long id) {
        if (!remindersRepository.existsById(id)) {
            throw new EntityNotFoundException("Reminder not found with id: " + id);
        }
        remindersRepository.deleteById(id);
    }
}
