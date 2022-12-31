package com.CarRent.reservationService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Date endDate;
    private Long userId;
    @ManyToOne
    private CompanyVehicleModel companyVehicleModel;
    private Long totalPrice;

    public Reservation(Date startDate, Date endDate, Long userId, CompanyVehicleModel companyVehicleModel, Long totalPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.companyVehicleModel = companyVehicleModel;
        this.totalPrice = totalPrice;
    }
}
