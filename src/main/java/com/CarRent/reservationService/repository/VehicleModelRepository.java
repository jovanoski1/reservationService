package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleModelRepository extends JpaRepository<Vehicle, Long> {
}
