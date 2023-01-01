package com.CarRent.reservationService.dto;

import com.CarRent.reservationService.model.Reservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewUpdateDto {
    private Long reviewId;
    private Long rating;
    private String comment;
}
