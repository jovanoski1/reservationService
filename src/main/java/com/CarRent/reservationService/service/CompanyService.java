package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;

public interface CompanyService {
    CompanyDto updateCompanyInfo(CompanyUpdateDto companyUpdateDto);
}
