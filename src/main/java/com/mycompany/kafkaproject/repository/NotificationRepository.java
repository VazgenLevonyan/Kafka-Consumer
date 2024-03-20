package com.mycompany.kafkaproject.repository;

import com.mycompany.kafkaproject.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}