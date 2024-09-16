package com.vitte.notification.service.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.service.Orkestrator;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationSubscriber {

    private final ObjectMapper objectMapper;

    private final Orkestrator orkestrator;


    @AsyncListener(operation = @AsyncOperation(
            channelName = "new.notification",
            message = @AsyncMessage( // Optional
                    messageId = "my-unique-id",
                    name = "NotificationDto",
                    description = "Example payload model for sending messages"
            )
    ))
    @JmsListener(destination = "new.notification")
    public void receiveTopic(String jsonMessage) {
        try {
            NotificationDto message = objectMapper.readValue(jsonMessage, NotificationDto.class);
            log.debug("Received message from topic: {}", message.getLinkToConversation());
            orkestrator.receiveNewNotification(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
