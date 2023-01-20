package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;
import com.CarRent.reservationService.security.CheckSecurity;
import com.CarRent.reservationService.security.service.TokenService;
import com.CarRent.reservationService.service.CompanyService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final TokenService tokenService;

    public CompanyController(CompanyService companyService, TokenService tokenService) {
        this.companyService = companyService;
        this.tokenService = tokenService;
    }

    @PutMapping ("/updateCompany")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<CompanyDto> updateCompanyInfo(@RequestBody @Validated CompanyUpdateDto companyUpdateDto, @RequestHeader String authorization) {
        companyUpdateDto.setManagerId(tokenService.parseId(authorization));
        return new ResponseEntity<>(companyService.updateCompanyInfo(companyUpdateDto), HttpStatus.OK);
    }
    @GetMapping ("/getInfo")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<CompanyDto> getCompanyInfo(@RequestHeader String authorization) {
        return new ResponseEntity<>(companyService.getCompanyInfo(tokenService.parseId(authorization)), HttpStatus.OK);
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
