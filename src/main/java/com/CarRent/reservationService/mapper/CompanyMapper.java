package com.CarRent.reservationService.mapper;

import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    private final CompanyRepository companyRepository;

    public CompanyMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company companyUpdateDtoToCompany(CompanyUpdateDto companyUpdateDto){
        Company company = companyRepository.getCompanyByManagerId(companyUpdateDto.getManagerId()).get();
        if(companyUpdateDto.getDescription()!=null) company.setDescription(companyUpdateDto.getDescription());
        if(companyUpdateDto.getName()!=null) company.setName(companyUpdateDto.getName());
        if(companyUpdateDto.getNumberOfVehicle()!=null) company.setNumberOfVehicle(companyUpdateDto.getNumberOfVehicle());
        return company;
    }

    public CompanyDto companyToCompanyDto(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setCity(company.getCity());
        companyDto.setDescription(company.getDescription());
        companyDto.setName(company.getName());
        companyDto.setNumberOfVehicle(company.getNumberOfVehicle());
        companyDto.setManagerId(company.getManagerId());
        return companyDto;
    }
}
