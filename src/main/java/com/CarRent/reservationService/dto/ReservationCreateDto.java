package com.CarRent.reservationService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {
    private Date startDate;
    private Date endDate;
    private Long userId;
    private Long companyVehicleModelId;
    private Long totalPrice;
}
