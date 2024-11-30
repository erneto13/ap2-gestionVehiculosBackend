package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {
}
