package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.CompanyAddTypeDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.VehicleTypeDto;

import java.util.List;

public interface CompanyTypeService {
    MessageDto add(CompanyAddTypeDto companyAddTypeDto);

    MessageDto delete(CompanyAddTypeDto companyAddTypeDto);

    List<VehicleTypeDto> getAllTypes();
}
