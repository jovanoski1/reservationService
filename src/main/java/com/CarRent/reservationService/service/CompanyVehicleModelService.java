package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.CompanyAddVehicleModelDto;
import com.CarRent.reservationService.dto.CompanyVehicleUpdatePriceDto;
import com.CarRent.reservationService.dto.MessageDto;

public interface CompanyVehicleModelService {
    MessageDto addModelToCompany(CompanyAddVehicleModelDto companyAddVehicleModelDto);

    MessageDto delete(Long id);

    MessageDto updatePrice(CompanyVehicleUpdatePriceDto companyVehicleUpdatePriceDto);
}
