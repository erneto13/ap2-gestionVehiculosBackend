package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.RemindersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// esta clase será para emplear métodos generales
public interface IUtilsRepository extends JpaRepository<RemindersModel, Long> {
}