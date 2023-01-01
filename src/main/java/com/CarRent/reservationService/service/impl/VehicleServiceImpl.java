package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.VehicleAddDto;
import com.CarRent.reservationService.dto.VehicleDeleteDto;
import com.CarRent.reservationService.dto.VehicleUpdateDto;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.Vehicle;
import com.CarRent.reservationService.model.VehicleType;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.VehicleModelRepository;
import com.CarRent.reservationService.repository.VehicleTypeRepository;
import com.CarRent.reservationService.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleModelRepository vehicleModelRepository;
    private final CompanyRepository companyRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleServiceImpl(VehicleModelRepository vehicleModelRepository, CompanyRepository companyRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleModelRepository = vehicleModelRepository;
        this.companyRepository = companyRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public MessageDto addVehicle(VehicleAddDto vehicleAddDto) {
        Company company = companyRepository.findById(vehicleAddDto.getCompanyId()).get();
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
}
