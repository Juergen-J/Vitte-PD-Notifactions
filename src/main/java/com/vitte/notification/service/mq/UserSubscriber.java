package com.vitte.notification.service.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitte.notification.dto.UserDto;
import com.vitte.notification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSubscriber {

    private final ObjectMapper objectMapper;

    private final UserService userService;

    @JmsListener(destination = "new.user")
    public void receiveTopic(String jsonMessage) {
        try {
            UserDto user = objectMapper.readValue(jsonMessage, UserDto.class);
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
