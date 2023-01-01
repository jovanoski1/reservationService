package com.CarRent.reservationService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchAvailableDto {
    private String city;
    private Long companyId;
    private Date startDate;
    private Date endDate;

}
