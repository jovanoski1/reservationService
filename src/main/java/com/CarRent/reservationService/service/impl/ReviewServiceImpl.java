package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.*;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.model.Review;
import com.CarRent.reservationService.repository.ReservationRepository;
import com.CarRent.reservationService.repository.ReviewRepository;
import com.CarRent.reservationService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public List<ReviewDto> getAll(Long id, String city) {
        List<ReviewDto> reviews = new ArrayList<>();

        for(Review review : reviewRepository.findAll()){
            if(review.getReservation().getCompany().getCity().equals(city) && Objects.equals(review.getReservation().getCompany().getId(), id)){
                ReviewDto reviewDto = new ReviewDto(review.getId(), review.getRating(), review.getComment(), review.getReservation());

                reviews.add(reviewDto);
            }
        }

        return reviews;
    }

    @Override
    public List<AverageRatingDto> getAverageRatings() {
        List<Review> reviews = reviewRepository.findAll();
        Map<Company, Double> map = new HashMap<>();
        Map<Company, Integer> mapCnt = new HashMap<>();
        for(Review r:reviews){
            map.putIfAbsent(r.getReservation().getCompany(), 0.0);
            mapCnt.putIfAbsent(r.getReservation().getCompany(), 0);
            map.put(r.getReservation().getCompany(), map.get(r.getReservation().getCompany()) + r.getRating());
            mapCnt.put(r.getReservation().getCompany(), mapCnt.get(r.getReservation().getCompany()) + 1 );
        }

        List<AverageRatingDto> avg = new ArrayList<>();

        for(Company company : map.keySet()){
            AverageRatingDto averageRatingDto = new AverageRatingDto();
            averageRatingDto.setCompany(company);
            averageRatingDto.setAvg(map.get(company)/mapCnt.get(company));

            avg.add(averageRatingDto);
        }
        avg.sort(new Comparator<AverageRatingDto>() {
            @Override
            public int compare(AverageRatingDto o1, AverageRatingDto o2) {
                if (o1.getAvg() >= o2.getAvg()) return -1;
                return 1;
            }
        });
        return avg;
    }

    @Override
    public List<ReviewDto> getReviewsForUser(Long id) {
        List<ReviewDto> res = new ArrayList<>();

        for(Review review: reviewRepository.findAll()){
            if(review.getReservation().getUserId().equals(id)){
                ReviewDto r = new ReviewDto(review.getId(), review.getRating(), review.getComment(), review.getReservation());
                res.add(r);
            }
        }
        return res;
    }
}
