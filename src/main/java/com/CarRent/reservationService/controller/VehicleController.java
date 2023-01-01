package com.CarRent.reservationService.controller;

import com.CarRent.reservationService.dto.*;
import com.CarRent.reservationService.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> addVehicle(@RequestBody @Validated VehicleAddDto vehicleAddDto){
        return new ResponseEntity<>(vehicleService.addVehicle(vehicleAddDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<MessageDto> deleteVehicle(@RequestBody @Validated VehicleDeleteDto vehicleAddDto){
        return new ResponseEntity<>(vehicleService.deleteVehicle(vehicleAddDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MessageDto> updateVehicle(@RequestBody @Validated VehicleUpdateDto vehicleAddDto){
        return new ResponseEntity<>(vehicleService.updateVehicle(vehicleAddDto), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<AvailableVehicleDto>> getCompanies(@RequestBody @Validated SearchAvailableDto searchAvailableDto) {
        return new ResponseEntity<>(vehicleService.search(searchAvailableDto), HttpStatus.OK);
    }


}
