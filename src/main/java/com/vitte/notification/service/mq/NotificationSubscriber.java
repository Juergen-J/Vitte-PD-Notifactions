package com.vitte.notification.service.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.service.Orkestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationSubscriber {

    private final ObjectMapper objectMapper;

    private final Orkestrator orkestrator;

    @JmsListener(destination = "new.notification")
    public void receiveTopic(String jsonMessage) {
        try {
            NotificationDto message = objectMapper.readValue(jsonMessage, NotificationDto.class);
            System.out.println("Received message from topic: " + message.getMessage());
            orkestrator.receiveNewNotification(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
