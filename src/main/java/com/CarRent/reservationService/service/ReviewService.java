package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.*;
import com.CarRent.reservationService.model.Review;

import java.util.List;

public interface ReviewService {

    MessageDto create(ReviewCreateDto reviewCreateDto);

    MessageDto update(ReviewUpdateDto reviewUpdateDto);

    MessageDto delete(ReviewDeleteDto reviewDeleteDto);

    List<ReviewDto> getAll(Long id, String city);

    List<AverageRatingDto> getAverageRatings();
}
