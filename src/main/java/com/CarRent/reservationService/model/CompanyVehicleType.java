package com.CarRent.reservationService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanyVehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Company company;
    @ManyToOne
    private VehicleType vehicleType;

    public CompanyVehicleType(Company company, VehicleType vehicleType) {
        this.company = company;
        this.vehicleType = vehicleType;
    }
}
