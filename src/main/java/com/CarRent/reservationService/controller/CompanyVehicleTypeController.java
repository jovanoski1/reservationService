package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.CompanyAddTypeDto;
import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.VehicleTypeDto;
import com.CarRent.reservationService.security.CheckSecurity;
import com.CarRent.reservationService.security.service.TokenService;
import com.CarRent.reservationService.service.CompanyTypeService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyVehicleType")
public class CompanyVehicleTypeController {

    private final CompanyTypeService companyTypeService;
    private final TokenService tokenService;
    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    public CompanyVehicleTypeController(CompanyTypeService companyTypeService, TokenService tokenService) {
        this.companyTypeService = companyTypeService;
        this.tokenService = tokenService;
    }

    @PostMapping()
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<MessageDto> addModelToCompany(@RequestBody @Validated CompanyAddTypeDto companyAddTypeDto, @RequestHeader("Authorization") String authorization){
        companyAddTypeDto.setManagerId(tokenService.parseId(authorization));
        return new ResponseEntity<>(companyTypeService.add(companyAddTypeDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<MessageDto> delete(@RequestBody @Validated CompanyAddTypeDto companyAddTypeDto, @RequestHeader("Authorization") String authorization){
        companyAddTypeDto.setManagerId(tokenService.parseId(authorization));
        return new ResponseEntity<>(companyTypeService.delete(companyAddTypeDto), HttpStatus.OK);
    }

    @GetMapping()
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<VehicleTypeDto>> getAllTypes() {
        return new ResponseEntity<>(companyTypeService.getAllTypes(), HttpStatus.OK);
    }
}
