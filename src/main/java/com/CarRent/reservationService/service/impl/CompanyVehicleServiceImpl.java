package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.CompanyAddVehicleModelDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.CompanyVehicleModel;
import com.CarRent.reservationService.model.VehicleModel;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.CompanyVehicleModelRepository;
import com.CarRent.reservationService.repository.VehicleModelRepository;
import com.CarRent.reservationService.service.CompanyVehicleModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyVehicleServiceImpl implements CompanyVehicleModelService {
    private final CompanyRepository companyRepository;
    private final VehicleModelRepository vehicleModelRepository;
    private final CompanyVehicleModelRepository companyVehicleModelRepository;

    public CompanyVehicleServiceImpl(CompanyRepository companyRepository, VehicleModelRepository vehicleModelRepository, CompanyVehicleModelRepository companyVehicleModelRepository) {
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.companyVehicleModelRepository = companyVehicleModelRepository;
    }

    @Override
    public MessageDto addModelToCompany(CompanyAddVehicleModelDto companyAddVehicleModelDto) {

        VehicleModel vehicleModel = vehicleModelRepository.findById(companyAddVehicleModelDto.getVehicleModelId()).get();
        Company company = companyRepository.findById(companyAddVehicleModelDto.getCompanyId()).get();

        CompanyVehicleModel companyVehicleModel = new CompanyVehicleModel(company,vehicleModel, companyAddVehicleModelDto.getPricePerDay());
        companyVehicleModelRepository.save(companyVehicleModel);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully added model to company");

        return messageDto;
    }
}
