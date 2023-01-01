package com.CarRent.reservationService.service;

import com.CarRent.reservationService.dto.*;

import java.util.List;

public interface VehicleService{

    MessageDto addVehicle(VehicleAddDto vehicleAddDto);

    MessageDto deleteVehicle(VehicleDeleteDto vehicleDeleteDto);

    MessageDto updateVehicle(VehicleUpdateDto vehicleUpdateDto);

    List<AvailableVehicleDto> search(SearchAvailableDto searchAvailableDto);
}
