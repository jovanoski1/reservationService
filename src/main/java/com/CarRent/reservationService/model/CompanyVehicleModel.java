package com.CarRent.reservationService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanyVehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Company company;
    @ManyToOne
    private VehicleModel vehicleModel;
    private Long pricePerDay;

    public CompanyVehicleModel(Company company, VehicleModel vehicleModel, Long pricePerDay) {
        this.company = company;
        this.vehicleModel = vehicleModel;
        this.pricePerDay = pricePerDay;
    }
}
