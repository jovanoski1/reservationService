package com.CarRent.reservationService.dto;

import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.Vehicle;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class ReservationDto {
    private Long id;

    private Date startDate;
    private Date endDate;
    private Long userId;
    private Vehicle vehicle;
    private Company company;
    private Long totalPrice;
}
