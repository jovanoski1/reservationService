package com.CarRent.reservationService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAddVehicleModelDto {
    private Long companyId;
    private Long vehicleModelId;
    private Long pricePerDay;
}
