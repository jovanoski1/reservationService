package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.CompanyAddTypeDto;
import com.CarRent.reservationService.dto.MessageDto;

public interface CompanyTypeService {
    MessageDto add(CompanyAddTypeDto companyAddTypeDto);

    MessageDto delete(CompanyAddTypeDto companyAddTypeDto);
}
