package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReviewCreateDto;
import com.CarRent.reservationService.dto.ReviewDeleteDto;
import com.CarRent.reservationService.dto.ReviewUpdateDto;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.model.Review;
import com.CarRent.reservationService.repository.ReservationRepository;
import com.CarRent.reservationService.repository.ReviewRepository;
import com.CarRent.reservationService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReservationRepository reservationRepository) {
        this.reviewRepository = reviewRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public MessageDto create(ReviewCreateDto reviewCreateDto) {

        Reservation reservation = reservationRepository.findById(reviewCreateDto.getReservationId()).get();

        Review review = new Review();
        review.setComment(reviewCreateDto.getComment());
        review.setRating(reviewCreateDto.getRating());
        review.setReservation(reservation);

        reviewRepository.save(review);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully created review");

        return messageDto;
    }

    @Override
    public MessageDto update(ReviewUpdateDto reviewUpdateDto) {

        Review review = reviewRepository.findById(reviewUpdateDto.getReviewId()).get();

        if(reviewUpdateDto.getComment()!=null)review.setComment(reviewUpdateDto.getComment());
        if(reviewUpdateDto.getRating()!=null)review.setRating(reviewUpdateDto.getRating());

        reviewRepository.save(review);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully updated review");

        return messageDto;
    }

    @Override
    public MessageDto delete(ReviewDeleteDto reviewDeleteDto) {
        Review review = reviewRepository.findById(reviewDeleteDto.getReviewId()).get();
        reviewRepository.delete(review);


        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully deleted review");

        return messageDto;
    }
}
