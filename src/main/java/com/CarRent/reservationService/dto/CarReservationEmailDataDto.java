package com.CarRent.reservationService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarReservationEmailDataDto {
    private Long userId;
    private Long managerId;
    private String notificationType;
    private String firstName;
    private String lastName;
    private String carBrand;
    private String carModel;
    private String registrationNum;
}
