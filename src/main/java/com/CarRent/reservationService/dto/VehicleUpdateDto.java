package com.CarRent.reservationService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleUpdateDto {
    private Long vehicleId;
    private Long pricePerDay;
    private String registration;
}
