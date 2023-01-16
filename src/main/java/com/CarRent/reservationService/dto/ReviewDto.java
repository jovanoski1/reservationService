package com.CarRent.reservationService.dto;

import com.CarRent.reservationService.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Long rating;
    private String comment;
    private Reservation reservation;
}
