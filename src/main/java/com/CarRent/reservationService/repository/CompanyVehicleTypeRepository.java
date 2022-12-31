package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.model.CompanyVehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyVehicleTypeRepository extends JpaRepository<CompanyVehicleType,Long> {


    Optional<CompanyVehicleType> findByVehicleTypeIdAndCompanyId(Long aLong, Long bLong);
}
