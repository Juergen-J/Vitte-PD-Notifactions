package com.vitte.notification.service;

import com.vitte.notification.dto.UserDto;
import com.vitte.notification.entity.UserEntity;
import com.vitte.notification.mapper.UserMapper;
import com.vitte.notification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository repo;

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);


    public void saveUser(UserDto userDto) {
        UserEntity savedUser = repo.save(mapper.mapUser(userDto));
        log.debug("user saved successful - {}", savedUser);
    }

    public Optional<UserEntity> getUser(long userId) {
        return repo.findByUserId(userId);
    }
}
