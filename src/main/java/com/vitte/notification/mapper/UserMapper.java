package com.vitte.notification.mapper;

import com.vitte.notification.dto.ContactDto;
import com.vitte.notification.dto.UserDto;
import com.vitte.notification.entity.Contact;
import com.vitte.notification.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {


    UserEntity mapUser(UserDto userDto);

    Contact mapContact(ContactDto contactDto);
}
