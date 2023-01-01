package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.dto.ReviewCreateDto;
import com.CarRent.reservationService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> createReview(@RequestBody @Validated ReviewCreateDto reviewCreateDto){
        return new ResponseEntity<>(reviewService.create(reviewCreateDto), HttpStatus.CREATED);
    }

}
