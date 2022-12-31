package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.ReservationCreateDto;
import com.CarRent.reservationService.model.CompanyVehicleModel;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.CompanyVehicleModelRepository;
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
    private final CompanyVehicleModelRepository companyVehicleModelRepository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(CompanyRepository companyRepository, VehicleModelRepository companyVehicleModelRepository, CompanyVehicleModelRepository companyVehicleModelRepository1, ReservationRepository reservationRepository) {
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = companyVehicleModelRepository;
        this.companyVehicleModelRepository = companyVehicleModelRepository1;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public MessageDto createReservation(ReservationCreateDto reservationCreateDto) {

        CompanyVehicleModel companyVehicleModel = companyVehicleModelRepository.findById(reservationCreateDto.getCompanyVehicleModelId()).get();

        Reservation reservation = new Reservation();
        reservation.setUserId(reservationCreateDto.getUserId());
        reservation.setStartDate(reservationCreateDto.getStartDate());
        reservation.setEndDate(reservationCreateDto.getEndDate());
        reservation.setCompanyVehicleModel(companyVehicleModel);

        long diff = Math.abs(reservation.getStartDate().getTime()-reservation.getEndDate().getTime());
        long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;

        //#TODO dodati racunanje popusta
        reservation.setTotalPrice(dayDiff*companyVehicleModel.getPricePerDay());

        reservationRepository.save(reservation);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully reserved model "+companyVehicleModel.getVehicleModel().getModel() + " in company "+companyVehicleModel.getCompany().getName());
        return messageDto;
    }
}
