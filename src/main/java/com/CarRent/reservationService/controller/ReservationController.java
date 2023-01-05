package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCancelDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.security.service.TokenService;
import com.CarRent.reservationService.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final TokenService tokenService;

    public ReservationController(ReservationService reservationService, TokenService tokenService) {
        this.reservationService = reservationService;
        this.tokenService = tokenService;
    }


    @PostMapping
    public ResponseEntity<MessageDto> createReservation(@RequestBody @Validated ReservationCreateDto reservationCreateDto, @RequestHeader String authorization){
        reservationCreateDto.setUserId(tokenService.parseId(authorization));
        return new ResponseEntity<>(reservationService.createReservation(reservationCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<MessageDto> cancelReservation(@RequestBody @Validated ReservationCancelDto reservationCancelDto){
        return new ResponseEntity<>(reservationService.cancelReservation(reservationCancelDto), HttpStatus.OK);
    }
}
