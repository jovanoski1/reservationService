package com.CarRent.reservationService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @ManyToOne
    private VehicleType vehicleType;

    public VehicleModel(String brand, String model, VehicleType vehicleType) {
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
    }
}
