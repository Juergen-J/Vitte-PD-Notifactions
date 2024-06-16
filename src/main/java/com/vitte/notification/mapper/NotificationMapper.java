package com.vitte.notification.mapper;

import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.entity.NotificationEntity;
import org.mapstruct.Mapper;

@Mapper
public interface NotificationMapper {

    NotificationEntity mapNotification(NotificationDto notificationDto);
}
