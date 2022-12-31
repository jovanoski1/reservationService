package com.CarRent.reservationService.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CompanyUpdateDto {

    private String name;
    private String description;
    private Long numberOfVehicle;
    private Long managerId;
}
