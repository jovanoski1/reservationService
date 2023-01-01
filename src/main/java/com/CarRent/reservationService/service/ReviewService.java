package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReviewCreateDto;
import com.CarRent.reservationService.dto.ReviewDeleteDto;
import com.CarRent.reservationService.dto.ReviewUpdateDto;

public interface ReviewService {

    MessageDto create(ReviewCreateDto reviewCreateDto);

    MessageDto update(ReviewUpdateDto reviewUpdateDto);

    MessageDto delete(ReviewDeleteDto reviewDeleteDto);

}
