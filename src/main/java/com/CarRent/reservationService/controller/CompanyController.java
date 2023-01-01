package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;
import com.CarRent.reservationService.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PutMapping ("/updateCompany")
    public ResponseEntity<CompanyDto> updateCompanyInfo(@RequestBody @Validated CompanyUpdateDto companyUpdateDto) {
        return new ResponseEntity<>(companyService.updateCompanyInfo(companyUpdateDto), HttpStatus.OK);
    }

    @GetMapping ("/getCities")
    public ResponseEntity<List<String>> getAllCities() {
        return new ResponseEntity<>(companyService.getAllCities(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

}
