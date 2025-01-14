package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.CompletedRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedRouteRepository extends JpaRepository<CompletedRoute, Long> {
    List<CompletedRoute> findByClientId(Long clientId);
}
