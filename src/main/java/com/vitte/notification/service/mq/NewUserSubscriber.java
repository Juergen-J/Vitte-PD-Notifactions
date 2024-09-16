package com.vitte.notification.service.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitte.notification.dto.PersonDto;
import com.vitte.notification.dto.UserDto;
import com.vitte.notification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewUserSubscriber {

    private static final String TOPIC_NAME = "new.user";
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @JmsListener(destination =TOPIC_NAME)
    public void receiveTopic(String jsonMessage) {
        try {
            PersonDto user = objectMapper.readValue(jsonMessage, PersonDto.class);
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
