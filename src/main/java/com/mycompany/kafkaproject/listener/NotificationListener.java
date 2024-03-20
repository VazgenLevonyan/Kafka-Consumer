package com.mycompany.kafkaproject.listener;

import com.mycompany.kafkaproject.model.dto.CreateNotificationDto;
import com.mycompany.kafkaproject.service.impl.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationServiceImpl notificationServiceImpl;

    @KafkaListener(
            id = "notificationListener",
            topics = "#{ '${kafka.activity.topic.name}' }",
            containerFactory = "kafkaListenerContainerFactory")
    public void createListener(@Payload(required = false) CreateNotificationDto notificationDto) {
        try {
            notificationServiceImpl.create(notificationDto);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
