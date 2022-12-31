package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyAddVehicleModelDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.model.CompanyVehicleModel;
import com.CarRent.reservationService.repository.CompanyVehicleModelRepository;
import com.CarRent.reservationService.service.CompanyVehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companyVehicleModel")
public class CompanyVehicleModelController {
    private final CompanyVehicleModelService companyVehicleModelService;

    public CompanyVehicleModelController(CompanyVehicleModelService companyVehicleModelService) {
        this.companyVehicleModelService = companyVehicleModelService;
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<MessageDto> addModelToCompany(@RequestBody @Validated CompanyAddVehicleModelDto companyAddVehicleModelDto){
        return new ResponseEntity<>(companyVehicleModelService.addModelToCompany(companyAddVehicleModelDto), HttpStatus.CREATED);
    }
}
