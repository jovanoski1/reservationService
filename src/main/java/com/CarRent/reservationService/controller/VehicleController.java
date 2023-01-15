package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.*;
import com.CarRent.reservationService.security.CheckSecurity;
import com.CarRent.reservationService.security.service.TokenService;
import com.CarRent.reservationService.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final TokenService tokenService;

    public VehicleController(VehicleService vehicleService, TokenService tokenService) {
        this.vehicleService = vehicleService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<MessageDto> addVehicle(@RequestBody @Validated VehicleAddDto vehicleAddDto, @RequestHeader String authorization){
        vehicleAddDto.setCompanyId(tokenService.parseId(authorization));
        return new ResponseEntity<>(vehicleService.addVehicle(vehicleAddDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<MessageDto> deleteVehicle(@RequestBody @Validated VehicleDeleteDto vehicleAddDto,@RequestHeader String authorization){
        return new ResponseEntity<>(vehicleService.deleteVehicle(vehicleAddDto), HttpStatus.OK);
    }

    @PutMapping
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<MessageDto> updateVehicle(@RequestBody @Validated VehicleUpdateDto vehicleAddDto,@RequestHeader String authorization){
        return new ResponseEntity<>(vehicleService.updateVehicle(vehicleAddDto), HttpStatus.OK);
    }

    @PostMapping("/search")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<AvailableVehicleDto>> getCompanies(@RequestBody @Validated SearchAvailableDto searchAvailableDto) {
        return new ResponseEntity<>(vehicleService.search(searchAvailableDto), HttpStatus.OK);
    }


}
