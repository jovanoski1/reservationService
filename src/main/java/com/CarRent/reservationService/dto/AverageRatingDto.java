package com.CarRent.reservationService.dto;

import com.CarRent.reservationService.model.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AverageRatingDto {
    Company company;
    Double avg;
}
