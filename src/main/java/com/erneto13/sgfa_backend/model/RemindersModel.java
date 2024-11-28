package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reminders")
public class RemindersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminders_id;
    private String vehicle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime due_date;

    private String task;
}