package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.FrequentClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrequentClientRepository extends JpaRepository<FrequentClient, Long> {
    List<FrequentClient> findByClientId(Long clientId);
}
