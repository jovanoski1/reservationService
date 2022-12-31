package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.CompanyAddVehicleModelDto;
import com.CarRent.reservationService.dto.MessageDto;

public interface CompanyVehicleModelService {
    MessageDto addModelToCompany(CompanyAddVehicleModelDto companyAddVehicleModelDto);

    MessageDto delete(Long companyId, Long vehicleId);
}
