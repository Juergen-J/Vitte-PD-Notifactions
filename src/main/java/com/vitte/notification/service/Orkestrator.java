package com.vitte.notification.service;

import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.entity.NotificationEntity;
import com.vitte.notification.entity.UserEntity;
import com.vitte.notification.service.notification.NotificationSenderFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class Orkestrator {

    private final UserService userService;
    private final NotificationService notificationService;
    private final NotificationSenderFacade notificationSenderFacade;


    public void receiveNewNotification(NotificationDto notificationDto) {
        Optional<UserEntity> user = userService.getUser(notificationDto.getUserId());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("user not exist_ with userId " + notificationDto.getUserId());
        } else {
            NotificationEntity savedNotification = notificationService.saveNotification(notificationDto);
            notificationSenderFacade.sendNotification(user.get(), savedNotification);
        }
    }
}
