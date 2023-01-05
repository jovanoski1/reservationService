package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.CompanyAddTypeDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.model.CompanyVehicleType;
import com.CarRent.reservationService.model.VehicleType;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.CompanyVehicleTypeRepository;
import com.CarRent.reservationService.repository.VehicleTypeRepository;
import com.CarRent.reservationService.service.CompanyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyTypeServiceImpl implements CompanyTypeService {
    private final CompanyVehicleTypeRepository companyVehicleTypeRepository;
    private final CompanyRepository companyRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    public CompanyTypeServiceImpl(CompanyVehicleTypeRepository companyVehicleTypeRepository, CompanyRepository companyRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.companyVehicleTypeRepository = companyVehicleTypeRepository;
        this.companyRepository = companyRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public MessageDto add(CompanyAddTypeDto companyAddTypeDto) {
        Company company = companyRepository.getCompanyByManagerId(companyAddTypeDto.getManagerId()).get();
        VehicleType vehicleType = vehicleTypeRepository.findById(companyAddTypeDto.getVehicleTypeId()).get();

        CompanyVehicleType companyVehicleType = new CompanyVehicleType(company,vehicleType);
        companyVehicleTypeRepository.save(companyVehicleType);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully added type to company");

        return messageDto;
    }

    @Override
    public MessageDto delete(CompanyAddTypeDto companyAddTypeDto) {

        Company company = companyRepository.getCompanyByManagerId(companyAddTypeDto.getManagerId()).get();

        CompanyVehicleType companyVehicleType = companyVehicleTypeRepository.findByVehicleTypeIdAndCompanyId(companyAddTypeDto.getVehicleTypeId(), company.getId()).get();

        companyVehicleTypeRepository.delete(companyVehicleType);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Successfully deleted type from company");

        return messageDto;
    }
}
