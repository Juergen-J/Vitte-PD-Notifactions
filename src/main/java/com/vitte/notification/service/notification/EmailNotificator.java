package com.vitte.notification.service.notification;

import com.vitte.notification.dto.ContactDto;
import com.vitte.notification.entity.NotificationEntity;
import com.vitte.notification.entity.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificator implements Notificator {
    private static final NotificationType TYPE = NotificationType.EMAIL;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean isSupportedType(NotificationType notificationType) {
        return notificationType == TYPE;
    }

    @Override
    public boolean sendNotification(ContactDto contact, NotificationEntity savedNotification) {
        try {
            if (contact.isEnabled()) {
                SimpleMailMessage message = getSimpleMailMessage(contact, savedNotification);
                mailSender.send(message);

                log.info("{}-notification sent successfully!", TYPE);
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

    private static SimpleMailMessage getSimpleMailMessage(ContactDto contact, NotificationEntity savedNotification) {
        String host = "https://vitte-core.up.railway.app";
        String link = host + savedNotification.getLinkToConversation();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(contact.getContactInfo());
        message.setFrom("Служба поддержки <ask.me.kontakt@gmail.com>");
        message.setSubject("У вас новый комментарий");
        message.setText("На вашу заявку был дан новый комментарий. Что увидеть подробности перейдите по ссылке: " + link);
        return message;
    }

}
