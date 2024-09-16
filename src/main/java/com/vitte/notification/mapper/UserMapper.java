package com.vitte.notification.mapper;

import com.vitte.notification.dto.ContactDto;
import com.vitte.notification.dto.PersonDto;
import com.vitte.notification.dto.UserDto;
import com.vitte.notification.entity.Contact;
import com.vitte.notification.entity.NotificationType;
import com.vitte.notification.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface UserMapper {


    UserEntity mapUser(UserDto userDto);

    @Mapping(target = "userId", source = "id")
    UserEntity mapUser(PersonDto personDto);


    @AfterMapping
    default void afterMappingUser(@MappingTarget UserEntity userEntity, PersonDto personDto) {
        userEntity.setContacts(List.of(new Contact(null, NotificationType.EMAIL, personDto.getEmail(), true)));
    }

    UserDto mapUser(UserEntity userEntity);

    Contact mapContact(ContactDto contactDto);
}
