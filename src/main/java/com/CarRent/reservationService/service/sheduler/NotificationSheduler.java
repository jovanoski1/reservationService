package com.CarRent.reservationService.service.sheduler;

import com.CarRent.reservationService.dto.ReminderNotificationDto;
import com.CarRent.reservationService.helper.MessageHelper;
import com.CarRent.reservationService.model.Reservation;
import com.CarRent.reservationService.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationSheduler {

    private final JmsTemplate jmsTemplate;
    private final String destination;
    private final ReservationRepository reservationRepository;
    private final MessageHelper messageHelper;

    public NotificationSheduler(JmsTemplate jmsTemplate, @Value("${destination.reminderNotification}") String destination, ReservationRepository reservationRepository, MessageHelper messageHelper){
        this.destination = destination;
        this.jmsTemplate = jmsTemplate;
        this.reservationRepository = reservationRepository;
        this.messageHelper = messageHelper;
    }


    @Scheduled(fixedDelay = 86400, initialDelay = 2000)
    void sheduleTask()
    {
        for(Reservation reservation: reservationRepository.findAll()){
            Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            long diff = Math.abs(reservation.getStartDate().getTime()- sqlDate.getTime());
            long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
            System.out.println(dayDiff + " id: " + reservation.getId());
            if(dayDiff<=3){
                ReminderNotificationDto reminderNotificationDto = new ReminderNotificationDto(reservation.getUserId(),"REMINDER_EMAIL");
                jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(reminderNotificationDto));
            }
        }
    }
}
