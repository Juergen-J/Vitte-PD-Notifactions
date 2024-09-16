package com.vitte.notification.service.notification;

import com.vitte.notification.dto.UserDto;
import com.vitte.notification.entity.NotificationEntity;
import com.vitte.notification.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationSenderFacade {

    private final List<Notificator> notificators;


    public void sendNotification(UserDto userEntity, NotificationEntity savedNotification) {
        userEntity.getContacts().forEach(contact -> {
            notificators.stream()
                    .filter(notificator -> notificator.isSupportedType(contact.getNotificationType()))
                    .forEach(notificator -> notificator.sendNotification(contact, savedNotification));
        });
    }

}
