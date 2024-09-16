package com.vitte.notification.service;

import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.dto.UserDto;
import com.vitte.notification.entity.NotificationEntity;
import com.vitte.notification.service.notification.NotificationSenderFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Orkestrator {

    private final UserService userService;
    private final NotificationService notificationService;
    private final NotificationSenderFacade notificationSenderFacade;


    public void receiveNewNotification(NotificationDto notificationDto) {
        try{
            UserDto user = userService.getUser(notificationDto.getUserId());
            NotificationEntity savedNotification = notificationService.saveNotification(notificationDto);
            notificationSenderFacade.sendNotification(user, savedNotification);
        }catch (IllegalArgumentException e){
            log.error("check log",e);
        }
    }
}
