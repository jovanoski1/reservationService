package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType,Long> {
}
