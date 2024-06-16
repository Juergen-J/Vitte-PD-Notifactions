package com.vitte.notification.service;

import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.entity.NotificationEntity;
import com.vitte.notification.mapper.NotificationMapper;
import com.vitte.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper mapper = Mappers.getMapper(NotificationMapper.class);


    public NotificationEntity saveNotification(NotificationDto notificationDto) {
        return notificationRepository.save(mapper.mapNotification(notificationDto));
    }
}
