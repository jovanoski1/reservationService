package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.*;
import com.CarRent.reservationService.model.Review;
import com.CarRent.reservationService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public ResponseEntity<MessageDto> updateReview(@RequestBody @Validated ReviewUpdateDto reviewUpdateDto){
        return new ResponseEntity<>(reviewService.update(reviewUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<MessageDto> deleteReview(@RequestBody @Validated ReviewDeleteDto reviewDeleteDto){
        return new ResponseEntity<>(reviewService.delete(reviewDeleteDto), HttpStatus.OK);
    }

    @GetMapping("/{id}/{city}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ReviewDto>> getAllReviews(@PathVariable Long id, @PathVariable String city){
        return new ResponseEntity<>(reviewService.getAll(id, city), HttpStatus.OK);
    }

    @GetMapping("/ratings")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<AverageRatingDto>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAverageRatings(), HttpStatus.OK);
    }
}
