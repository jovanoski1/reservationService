package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCancelDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.model.Vehicle;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.ReservationRepository;
import com.CarRent.reservationService.repository.VehicleModelRepository;
import com.CarRent.reservationService.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final CompanyRepository companyRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(CompanyRepository companyRepository, VehicleModelRepository companyVehicleModelRepository, ReservationRepository reservationRepository) {
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = companyVehicleModelRepository;
        this.reservationRepository = reservationRepository;
    }

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

        //#TODO dodati racunanje popusta
        reservation.setTotalPrice(dayDiff*companyVehicleModel.getPricePerDay());

        reservationRepository.save(reservation);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully reserved model "+ companyVehicleModel.getModel() + " in company "+companyVehicleModel.getCompany().getName());
        return messageDto;
    }

    @Override
    public MessageDto cancelReservation(ReservationCancelDto reservationCancelDto) {
        Reservation reservation = reservationRepository.findById(reservationCancelDto.getId()).get();
        reservationRepository.delete(reservation);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully canceled reservation");

        return messageDto;
    }
}
