package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT * FROM reservationServiceDB.vehicle ss JOIN reservationServiceDB.company s ON(ss.company_id=s.id)  WHERE s.city = ?1 AND s.id = ?2", nativeQuery = true)
    List<Vehicle> findAvailableVehicle(String city, Long companyId, Date start, Date end);
}
