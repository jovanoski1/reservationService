package com.CarRent.reservationService.dto;

import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableVehicleDto {
    private Long id;
    private String brand;
    private String model;
    private String registration;
    private VehicleType vehicleType;
    private Company company;
    private Long pricePerDay;
}
