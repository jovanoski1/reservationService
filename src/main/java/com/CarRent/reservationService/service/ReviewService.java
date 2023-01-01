package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReviewCreateDto;

public interface ReviewService {

    MessageDto create(ReviewCreateDto reviewCreateDto);

}
