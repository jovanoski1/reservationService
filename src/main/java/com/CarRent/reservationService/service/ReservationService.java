package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCancelDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;

public interface ReservationService {

    MessageDto createReservation(ReservationCreateDto reservationCreateDto);

    MessageDto cancelReservation(ReservationCancelDto reservationCancelDto);

}
