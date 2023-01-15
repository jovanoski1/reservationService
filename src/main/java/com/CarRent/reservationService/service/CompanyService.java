package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;

import java.util.List;

public interface CompanyService {
    CompanyDto updateCompanyInfo(CompanyUpdateDto companyUpdateDto);

    List<String> getAllCities();

    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyInfo(Long id);
}
