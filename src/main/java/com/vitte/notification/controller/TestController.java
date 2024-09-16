package com.vitte.notification.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitte.notification.dto.NotificationDto;
import com.vitte.notification.dto.UserDto;
import com.vitte.notification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class TestController {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper mapper;

    private final UserService userService;

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

    @GetMapping("api/v1/notification-api/users/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
