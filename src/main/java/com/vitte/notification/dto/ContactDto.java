package com.vitte.notification.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vitte.notification.entity.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDto {

    private NotificationType notificationType;

    private String contactInfo;

    private boolean enabled;
}
