package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.CompanyAddVehicleModelDto;
import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;
import com.CarRent.reservationService.dto.MessageDto;

public interface CompanyService {
    CompanyDto updateCompanyInfo(CompanyUpdateDto companyUpdateDto);

}
