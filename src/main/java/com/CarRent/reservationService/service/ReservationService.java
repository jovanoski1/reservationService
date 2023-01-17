package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCancelDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    MessageDto createReservation(ReservationCreateDto reservationCreateDto);

    MessageDto cancelReservation(ReservationCancelDto reservationCancelDto);

    List<ReservationDto> getReservations(Long id);

    List<ReservationDto> getReservationsNotReviews(Long id);
}
