package com.mycompany.kafkaproject.service.impl;

import com.mycompany.kafkaproject.mapper.NotificationMapper;
import com.mycompany.kafkaproject.model.dto.CreateNotificationDto;
import com.mycompany.kafkaproject.model.entity.Notification;
import com.mycompany.kafkaproject.repository.NotificationRepository;
import com.mycompany.kafkaproject.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    @Transactional
    public void create(CreateNotificationDto notificationDto) {
        Notification notification = notificationMapper.sourceToDestination(notificationDto);
        notificationRepository.save(notification);
    }
}
