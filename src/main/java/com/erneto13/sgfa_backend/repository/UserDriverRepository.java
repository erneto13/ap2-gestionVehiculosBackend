package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.UserDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDriverRepository extends JpaRepository<UserDriver, Integer> {
}