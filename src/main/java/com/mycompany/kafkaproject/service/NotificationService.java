package com.mycompany.kafkaproject.service;

import com.mycompany.kafkaproject.model.dto.CreateNotificationDto;

public interface NotificationService {

    void create(CreateNotificationDto notificationDto);
}
