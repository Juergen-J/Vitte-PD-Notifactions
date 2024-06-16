package com.vitte.notification.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper mapper;

    @PostMapping("/send/user")
    public String sendToTopic(@RequestBody UserDto message) {
        try {
            String jsonMessage = mapper.writeValueAsString(message);
            jmsTemplate.convertAndSend("new.user", jsonMessage);
            return "Message sent to topic!";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to send message";
        }
    }

    @PostMapping("/send/notification")
    public String sendToTopic(@RequestBody NotificationDto message) {
        try {
            String jsonMessage = mapper.writeValueAsString(message);
            jmsTemplate.convertAndSend("new.notification", jsonMessage);
            return "Message sent to topic!";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to send message";
        }
    }
}
