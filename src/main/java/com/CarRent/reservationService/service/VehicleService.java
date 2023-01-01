package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.MessageDto;
import com.CarRent.reservationService.dto.VehicleAddDto;
import com.CarRent.reservationService.dto.VehicleDeleteDto;
import com.CarRent.reservationService.dto.VehicleUpdateDto;

public interface VehicleService{

    MessageDto addVehicle(VehicleAddDto vehicleAddDto);

    MessageDto deleteVehicle(VehicleDeleteDto vehicleDeleteDto);

    MessageDto updateVehicle(VehicleUpdateDto vehicleUpdateDto);
}
