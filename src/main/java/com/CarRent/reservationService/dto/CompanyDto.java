package com.CarRent.reservationService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {

    private Long id;
    private String name;
    private String city;
    private String description;
    private Long numberOfVehicle;
    private Long managerId;
}
