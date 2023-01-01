package com.CarRent.reservationService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAddDto {
    private String brand;
    private String model;
    private String registration;
    private Long vehicleTypeId;
    private Long companyId;
    private Long pricePerDay;
}
