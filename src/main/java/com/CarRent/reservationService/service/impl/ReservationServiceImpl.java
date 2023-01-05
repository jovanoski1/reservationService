package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.Configuration.ClientDto;
import com.CarRent.reservationService.dto.CarReservationEmailDataDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCancelDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.helper.MessageHelper;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.model.Vehicle;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.ReservationRepository;
import com.CarRent.reservationService.repository.VehicleModelRepository;
import com.CarRent.reservationService.service.ReservationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final CompanyRepository companyRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(CompanyRepository companyRepository, VehicleModelRepository vehicleModelRepository,
                                  ReservationRepository reservationRepository, RestTemplate userServiceApiClient, MessageHelper messageHelper,
                                  JmsTemplate jmsTemplate, @Value("${destination.carRentNotification}") String destination) {
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.reservationRepository = reservationRepository;
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

        ResponseEntity<Long> discount = userServiceApiClient.exchange("/api/client/rank/" + reservationCreateDto.getUserId(), HttpMethod.GET,
                null, Long.class);

        reservation.setTotalPrice((long)(dayDiff*companyVehicleModel.getPricePerDay() * (100-discount.getBody())/100));

        reservationRepository.save(reservation);


        CarReservationEmailDataDto carReservationEmailDataDto = new CarReservationEmailDataDto();
        carReservationEmailDataDto.setCarBrand(companyVehicleModel.getBrand());
        carReservationEmailDataDto.setCarModel(companyVehicleModel.getModel());
        carReservationEmailDataDto.setRegistrationNum(companyVehicleModel.getRegistration());
        carReservationEmailDataDto.setManagerId(company.getManagerId());
        carReservationEmailDataDto.setUserId(reservationCreateDto.getUserId());
        carReservationEmailDataDto.setNotificationType("CAR_RESERVATION_EMAIL");
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(carReservationEmailDataDto));


        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully reserved model "+ companyVehicleModel.getModel() + " in company "+companyVehicleModel.getCompany().getName());
        return messageDto;
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
        carReservationEmailDataDto.setNotificationType("CANCEL_CAR_RESERVATION_EMAIL");
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(carReservationEmailDataDto));

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully canceled reservation");

        return messageDto;
    }
}
