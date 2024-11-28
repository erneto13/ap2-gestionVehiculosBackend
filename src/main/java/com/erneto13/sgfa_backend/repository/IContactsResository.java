package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IContactsResository extends JpaRepository<ContactModel, Long> {
}
