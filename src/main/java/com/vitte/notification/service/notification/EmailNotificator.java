package com.vitte.notification.service.notification;

import com.vitte.notification.entity.Contact;
import com.vitte.notification.entity.NotificationEntity;
import com.vitte.notification.entity.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificator implements Notificator {
    private static final NotificationType TYPE = NotificationType.EMAIL;

    @Override
    public boolean isSupportedType(NotificationType notificationType) {
        return notificationType == TYPE;
    }

    @Override
    public boolean sendNotification(Contact contact, NotificationEntity savedNotification) {
        try {
            if (contact.isEnabled()) {
                //todo implement here an email sender
                log.debug("{}-notification sent successful!", TYPE);
                return true;
            } else {
                log.debug("{}-notification disabled for user {}", TYPE, savedNotification.getUserId());
                return false;
            }

        } catch (Exception e) {
            log.error("{}-Notification was not send", TYPE, e);
            return false;
        }
    }

}
