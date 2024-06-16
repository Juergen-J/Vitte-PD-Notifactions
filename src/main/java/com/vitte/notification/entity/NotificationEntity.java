package com.vitte.notification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class NotificationEntity {

    @Id
    private Long userId;
}
