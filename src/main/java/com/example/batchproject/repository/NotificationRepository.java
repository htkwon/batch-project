package com.example.batchproject.repository;

import com.example.batchproject.entity.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Integer> {
}
