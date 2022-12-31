package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.model.CompanyVehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyVehicleTypeRepository extends JpaRepository<CompanyVehicleType,Long> {
}
