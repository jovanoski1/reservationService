package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.model.CompanyVehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyVehicleModelRepository extends JpaRepository<CompanyVehicleModel,Long> {
}
