package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> getCompanyByManagerId(Long aLong);

}
