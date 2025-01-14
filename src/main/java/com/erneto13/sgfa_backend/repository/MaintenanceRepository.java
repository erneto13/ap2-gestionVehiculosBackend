package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.MaintenanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceModel, Long> {
    List<MaintenanceModel> findByVehicleId(Long vehicleId);
}