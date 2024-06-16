package com.vitte.notification.service.notification;

import com.vitte.notification.entity.Contact;
import com.vitte.notification.entity.NotificationEntity;
import com.vitte.notification.entity.NotificationType;

public interface Notificator {

    boolean isSupportedType(NotificationType notificationType);
    boolean sendNotification(Contact contact, NotificationEntity savedNotification);

}
