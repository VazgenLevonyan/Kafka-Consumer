package com.mycompany.kafkaproject.mapper;

import com.mycompany.kafkaproject.model.dto.CreateNotificationDto;
import com.mycompany.kafkaproject.model.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "id", ignore = true)
    Notification sourceToDestination(CreateNotificationDto notificationDto);
}
