package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.*;
import com.CarRent.reservationService.exception.NotFoundException;
import com.CarRent.reservationService.helper.MessageHelper;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.model.Review;
import com.CarRent.reservationService.model.Vehicle;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.ReservationRepository;
import com.CarRent.reservationService.repository.ReviewRepository;
import com.CarRent.reservationService.repository.VehicleModelRepository;
import com.CarRent.reservationService.service.ReservationService;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final CompanyRepository companyRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;
    private final Retry userServiceRetry;

    public ReservationServiceImpl(CompanyRepository companyRepository, VehicleModelRepository vehicleModelRepository,
                                  ReservationRepository reservationRepository, ReviewRepository reviewRepository, Retry userServiceRetry, RestTemplate userServiceApiClient, MessageHelper messageHelper,
                                  JmsTemplate jmsTemplate, @Value("${destination.carRentNotification}") String destination) {
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.reservationRepository = reservationRepository;
        this.reviewRepository = reviewRepository;
        this.userServiceRetry = userServiceRetry;
        this.userServiceApiClient = userServiceApiClient;
        this.messageHelper = messageHelper;
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
    }

    private final RestTemplate userServiceApiClient;

    private MessageHelper messageHelper;
    private JmsTemplate jmsTemplate;
    private String destination;


    @Override
    public MessageDto createReservation(ReservationCreateDto reservationCreateDto) {

        Vehicle companyVehicleModel = vehicleModelRepository.findById(reservationCreateDto.getVehicleId()).get();
        Company company = companyRepository.findById(reservationCreateDto.getCompanyId()).get();


        Reservation reservation = new Reservation();
        reservation.setUserId(reservationCreateDto.getUserId());
        reservation.setStartDate(reservationCreateDto.getStartDate());
        reservation.setEndDate(reservationCreateDto.getEndDate());
        reservation.setCompany(company);
        reservation.setVehicle(companyVehicleModel);

        long diff = Math.abs(reservation.getStartDate().getTime()-reservation.getEndDate().getTime());
        long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

        Long discount = Retry.decorateSupplier(userServiceRetry, ()-> getDiscountForUser(reservationCreateDto.getUserId())).get();

        reservation.setTotalPrice((long)(dayDiff*companyVehicleModel.getPricePerDay() * (100-discount)/100));

        reservationRepository.save(reservation);


        CarReservationEmailDataDto carReservationEmailDataDto = new CarReservationEmailDataDto();
        carReservationEmailDataDto.setCarBrand(companyVehicleModel.getBrand());
        carReservationEmailDataDto.setCarModel(companyVehicleModel.getModel());
        carReservationEmailDataDto.setRegistrationNum(companyVehicleModel.getRegistration());
        carReservationEmailDataDto.setManagerId(company.getManagerId());
        carReservationEmailDataDto.setUserId(reservationCreateDto.getUserId());
        carReservationEmailDataDto.setNotificationType("CAR_RESERVATION_EMAIL");
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(carReservationEmailDataDto));

        ClientUpdateNumberOfRentDaysDto clientUpdateNumberOfRentDaysDto = new ClientUpdateNumberOfRentDaysDto(reservation.getUserId(),dayDiff);
        ResponseEntity<String> msg = userServiceApiClient.postForEntity("/api/client/increment/", clientUpdateNumberOfRentDaysDto,
                String.class);


        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully reserved model "+ companyVehicleModel.getModel() + " in company "+companyVehicleModel.getCompany().getName());
        return messageDto;
    }

    private Long getDiscountForUser(Long userId) {
        //get projection from movie service
        System.out.println("[" + System.currentTimeMillis() / 1000 + "] Getting discount for user id: " + userId);
        try {
            Thread.sleep(5000);
            return userServiceApiClient.exchange("/api/client/rank/" + userId, HttpMethod.GET,
                    null, Long.class).getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("User with id: %d not found.", userId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("Internal server error.");
        }
    }


    @Override
    public MessageDto cancelReservation(ReservationCancelDto reservationCancelDto) {
        Reservation reservation = reservationRepository.findById(reservationCancelDto.getId()).get();
        reservationRepository.delete(reservation);

        CarReservationEmailDataDto carReservationEmailDataDto = new CarReservationEmailDataDto();
        carReservationEmailDataDto.setCarBrand(reservation.getVehicle().getBrand());
        carReservationEmailDataDto.setCarModel(reservation.getVehicle().getModel());
        carReservationEmailDataDto.setRegistrationNum(reservation.getVehicle().getRegistration());
        carReservationEmailDataDto.setManagerId(reservation.getCompany().getManagerId());
        carReservationEmailDataDto.setUserId(reservation.getUserId());
        carReservationEmailDataDto.setNotificationType("CANCEL_RESERVATION_EMAIL");
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(carReservationEmailDataDto));

        long diff = Math.abs(reservation.getStartDate().getTime()-reservation.getEndDate().getTime());
        long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        ClientUpdateNumberOfRentDaysDto clientUpdateNumberOfRentDaysDto = new ClientUpdateNumberOfRentDaysDto(reservation.getUserId(),dayDiff);
        ResponseEntity<String> msg = userServiceApiClient.postForEntity("/api/client/decrement/", clientUpdateNumberOfRentDaysDto,
                String.class);
        System.out.println(dayDiff + " dayDiff");

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully canceled reservation");

        return messageDto;
    }

    @Override
    public List<ReservationDto> getReservations(Long id) {

        List<Reservation> reservations = reservationRepository.findAllByUserId(id);
        List<ReservationDto> res = new ArrayList<>();
        for(Reservation reservation: reservations){
            ReservationDto r = new ReservationDto();
            r.setId(reservation.getId());
            r.setVehicle(reservation.getVehicle());
            r.setCompany(reservation.getCompany());
            r.setTotalPrice(reservation.getTotalPrice());
            r.setEndDate(reservation.getEndDate());
            r.setStartDate(reservation.getStartDate());
            r.setUserId(id);
            res.add(r);
        }

        return res;
    }

    @Override
    public List<ReservationDto> getReservationsNotReviews(Long id) {
        List<Reservation> reservations = reservationRepository.findAllByUserId(id);
        List<Review>  reviews = reviewRepository.findAll();

        List<ReservationDto> res = new ArrayList<>();


        for(Reservation reservation: reservations){
            boolean exist = false;
            for(Review review:reviews){
                if(review.getReservation().getId().equals(reservation.getId())){
                    exist = true;
                    break;
                }
            }
            if(!exist){
                ReservationDto r = new ReservationDto();
                r.setId(reservation.getId());
                r.setVehicle(reservation.getVehicle());
                r.setCompany(reservation.getCompany());
                r.setTotalPrice(reservation.getTotalPrice());
                r.setEndDate(reservation.getEndDate());
                r.setStartDate(reservation.getStartDate());
                r.setUserId(id);
                res.add(r);
            }

        }
        return res;
    }
}
