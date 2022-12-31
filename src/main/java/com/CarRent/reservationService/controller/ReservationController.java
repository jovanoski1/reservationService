package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyAddTypeDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public ResponseEntity<MessageDto> addModelToCompany(@RequestBody @Validated ReservationCreateDto reservationCreateDto){
        return new ResponseEntity<>(reservationService.createReservation(reservationCreateDto), HttpStatus.CREATED);
    }
}
