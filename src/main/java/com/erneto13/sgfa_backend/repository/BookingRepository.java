package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.dto.RoutePoint;
import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long> {
    List<BookingModel> findByVehicle(VehicleModel vehicle);
    List<BookingModel> findByDriverName(String name);
    List<BookingModel> findByStatus(String status);
    List<BookingModel> findByDriverNameAndStatusNotIn(String name, List<String> statuses);
}
