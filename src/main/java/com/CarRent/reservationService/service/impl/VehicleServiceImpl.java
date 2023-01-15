package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.*;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.model.Vehicle;
import com.CarRent.reservationService.model.VehicleType;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.ReservationRepository;
import com.CarRent.reservationService.repository.VehicleModelRepository;
import com.CarRent.reservationService.repository.VehicleTypeRepository;
import com.CarRent.reservationService.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleModelRepository vehicleModelRepository;
    private final CompanyRepository companyRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final ReservationRepository reservationRepository;

    public VehicleServiceImpl(VehicleModelRepository vehicleModelRepository, CompanyRepository companyRepository, VehicleTypeRepository vehicleTypeRepository, ReservationRepository reservationRepository) {
        this.vehicleModelRepository = vehicleModelRepository;
        this.companyRepository = companyRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public MessageDto addVehicle(VehicleAddDto vehicleAddDto) {
        Company company = companyRepository.getCompanyByManagerId(vehicleAddDto.getCompanyId()).get();
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleAddDto.getVehicleTypeId()).get();

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleType);
        vehicle.setCompany(company);
        vehicle.setModel(vehicleAddDto.getModel());
        vehicle.setBrand(vehicleAddDto.getBrand());
        vehicle.setRegistration(vehicleAddDto.getRegistration());
        vehicle.setPricePerDay(vehicleAddDto.getPricePerDay());

        vehicleModelRepository.save(vehicle);

        MessageDto messageDto = new MessageDto("Successfully added vehicle to company!");

        return messageDto;
    }

    @Override
    public MessageDto deleteVehicle(VehicleDeleteDto vehicleDeleteDto) {

        Vehicle vehicle = vehicleModelRepository.findById(vehicleDeleteDto.getVehicleId()).get();
        vehicleModelRepository.delete(vehicle);

        return new MessageDto("Successfully deleted vehicle from company");
    }

    @Override
    public MessageDto updateVehicle(VehicleUpdateDto vehicleUpdateDto) {
        Vehicle vehicle = vehicleModelRepository.findById(vehicleUpdateDto.getVehicleId()).get();

        if(vehicleUpdateDto.getRegistration()!=null) vehicle.setRegistration(vehicleUpdateDto.getRegistration());
        if(vehicleUpdateDto.getPricePerDay()!=null) vehicle.setPricePerDay(vehicleUpdateDto.getPricePerDay());

        vehicleModelRepository.save(vehicle);

        return new MessageDto("Successfully updated vehicle");
    }

    @Override
    public List<AvailableVehicleDto> search(SearchAvailableDto searchAvailableDto) {

        List<AvailableVehicleDto> vehicles = new ArrayList<>();

        for(Vehicle vehicle : vehicleModelRepository.findAvailableVehicle(searchAvailableDto.getCity(), searchAvailableDto.getCompanyId(), searchAvailableDto.getStartDate(), searchAvailableDto.getEndDate())){
            System.out.println(vehicle.getModel() + " " + reservationRepository.findAllByVehicleId(vehicle.getId()).size());
            if (reservationRepository.findAllByVehicleId(vehicle.getId()).size() == 0){
                vehicleToAvailableVehicleDto(vehicles, vehicle);
                continue;
            }
            int cnt = 0;
            for(Reservation reservation:reservationRepository.findAllByVehicleId(vehicle.getId())){
                System.out.println(reservation.getId());
                // pocetak pre pocetka i kraj posle pocetka a a1 b b1
                if(searchAvailableDto.getStartDate().getTime()<=reservation.getStartDate().getTime() && searchAvailableDto.getEndDate().getTime()>=reservation.getStartDate().getTime()) break;
                // pocetak pre pocetka i kraj posle kraja a a1 b1 b
                if(searchAvailableDto.getStartDate().getTime()<=reservation.getStartDate().getTime() && searchAvailableDto.getEndDate().getTime()>=reservation.getEndDate().getTime()) break;
                // pocetak posle pocetka i kraj pre kraja a1 a b b1
                if(searchAvailableDto.getStartDate().getTime()>= reservation.getStartDate().getTime() && searchAvailableDto.getEndDate().getTime()<=reservation.getEndDate().getTime()) break;
                // pocetak posle pocetka i kraj posle kraja a a1 b1 b
                if(searchAvailableDto.getStartDate().getTime()>=reservation.getStartDate().getTime() && searchAvailableDto.getStartDate().getTime()<=reservation.getEndDate().getTime() && searchAvailableDto.getEndDate().getTime()>=reservation.getStartDate().getTime()) break;

                System.out.println("prosao " + vehicle.getModel() + " " + reservation.getId());
                cnt++;
                break;
            }
            if(cnt == reservationRepository.findAllByVehicleId(vehicle.getId()).size())vehicleToAvailableVehicleDto(vehicles, vehicle);
        }
        System.out.println(vehicles.size());
        return vehicles;
    }

    private void vehicleToAvailableVehicleDto(List<AvailableVehicleDto> vehicles, Vehicle vehicle) {
        AvailableVehicleDto availableVehicleDto = new AvailableVehicleDto();
        availableVehicleDto.setRegistration(vehicle.getRegistration());
        availableVehicleDto.setVehicleType(vehicle.getVehicleType());
        availableVehicleDto.setId(vehicle.getId());
        availableVehicleDto.setModel(vehicle.getModel());
        availableVehicleDto.setCompany(vehicle.getCompany());
        availableVehicleDto.setBrand(vehicle.getBrand());
        availableVehicleDto.setPricePerDay(vehicle.getPricePerDay());

        vehicles.add(availableVehicleDto);
    }
}
