package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyAddVehicleModelDto;
import com.CarRent.reservationService.dto.CompanyDto;
import com.CarRent.reservationService.dto.CompanyUpdateDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.security.CheckSecurity;
import com.CarRent.reservationService.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

}
