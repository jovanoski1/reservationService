package com.CarRent.reservationService.service.impl;

import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;
import com.CarRent.reservationService.mapper.CompanyMapper;
import com.CarRent.reservationService.model.Company;
import com.CarRent.reservationService.repository.CompanyRepository;
import com.CarRent.reservationService.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyMapper companyMapper, CompanyRepository companyRepository) {
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDto updateCompanyInfo(CompanyUpdateDto companyUpdateDto) {

        Company company = companyMapper.companyUpdateDtoToCompany(companyUpdateDto);
        companyRepository.save(company);
        return companyMapper.companyToCompanyDto(company);
    }
}
