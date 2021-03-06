package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.entity.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
