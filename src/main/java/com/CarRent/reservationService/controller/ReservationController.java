package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyAddTypeDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCancelDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public ResponseEntity<MessageDto> createReservation(@RequestBody @Validated ReservationCreateDto reservationCreateDto){
        return new ResponseEntity<>(reservationService.createReservation(reservationCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<MessageDto> cancelReservation(@RequestBody @Validated ReservationCancelDto reservationCancelDto){
        return new ResponseEntity<>(reservationService.cancelReservation(reservationCancelDto), HttpStatus.OK);
    }
}
