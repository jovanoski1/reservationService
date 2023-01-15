package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;
import com.CarRent.reservationService.mapper.CompanyMapper;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.repository.VehicleModelRepository;
import com.CarRent.reservationService.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
    private final VehicleModelRepository vehicleModelRepository;

    public CompanyServiceImpl(CompanyMapper companyMapper, CompanyRepository companyRepository, VehicleModelRepository vehicleModelRepository) {
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
        this.vehicleModelRepository = vehicleModelRepository;
    }

    @Override
    public CompanyDto updateCompanyInfo(CompanyUpdateDto companyUpdateDto) {

        Company company = companyMapper.companyUpdateDtoToCompany(companyUpdateDto);
        companyRepository.save(company);
        return companyMapper.companyToCompanyDto(company);
    }

    @Override
    public List<String> getAllCities() {
        List<String> cities = new ArrayList<>();

        for(Company company: companyRepository.findAll()){
            if(!cities.contains(company.getCity())) cities.add(company.getCity());
        }

        return cities;
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<CompanyDto> companyDtos = new ArrayList<>();
        for(Company company: companyRepository.findAll()) companyDtos.add(companyMapper.companyToCompanyDto(company));
        return companyDtos;
    }

    @Override
    public CompanyDto getCompanyInfo(Long id) {
        Company company = companyRepository.getCompanyByManagerId(id).get();
        return companyMapper.companyToCompanyDto(company);
    }


}
