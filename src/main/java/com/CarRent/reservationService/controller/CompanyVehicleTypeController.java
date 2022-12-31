package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyAddTypeDto;
import com.CarRent.reservationService.dto.CompanyAddVehicleModelDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.service.CompanyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companyVehicleType")
public class CompanyVehicleTypeController {

    private final CompanyTypeService companyTypeService;

    public CompanyVehicleTypeController(CompanyTypeService companyTypeService) {
        this.companyTypeService = companyTypeService;
    }

    @PostMapping()
    public ResponseEntity<MessageDto> addModelToCompany(@RequestBody @Validated CompanyAddTypeDto companyAddTypeDto){
        return new ResponseEntity<>(companyTypeService.add(companyAddTypeDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<MessageDto> delete(@RequestBody @Validated CompanyAddTypeDto companyAddTypeDto){
        return new ResponseEntity<>(companyTypeService.delete(companyAddTypeDto), HttpStatus.OK);
    }
}
