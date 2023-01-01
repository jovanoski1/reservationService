package com.CarRent.reservationService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "registration", unique = true)})
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String registration;
    @ManyToOne
    private VehicleType vehicleType;
    @ManyToOne
    private Company company;
    private Long pricePerDay;

    public Vehicle(String brand, String model, String registration, VehicleType vehicleType, Company company, Long pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.registration = registration;
        this.vehicleType = vehicleType;
        this.company = company;
        this.pricePerDay = pricePerDay;
    }
}
