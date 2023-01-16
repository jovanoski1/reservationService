package com.CarRent.reservationService.repository;

import com.CarRent.reservationService.dto.AverageRatingDto;
import com.CarRent.reservationService.model.Review;
import com.CarRent.reservationService.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query(value = "SELECT company_id, avg(rating) FROM reservationServiceDB.review ss JOIN reservationServiceDB.reservation s ON(ss.reservation_id=s.id) group by company_id order by avg(rating) desc ", nativeQuery = true)
    List<Review> getAverageRatings();
}
