package com.CarRent.reservationService.Configuration;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ClientDto {
    //all users
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String email;


    //client
    private String passportNumber;
    private Long rentDaysNumber;
    private Boolean restricted;
}
