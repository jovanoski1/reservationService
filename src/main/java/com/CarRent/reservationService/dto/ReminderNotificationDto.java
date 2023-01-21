package com.CarRent.reservationService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReminderNotificationDto {
    private Long userId;
    private String notificationType;
}
