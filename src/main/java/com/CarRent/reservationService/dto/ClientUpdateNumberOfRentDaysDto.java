package com.CarRent.reservationService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateNumberOfRentDaysDto {
    private Long userId;
    private Long numOfDaysToAdd;
}
