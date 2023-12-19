package com.example.homeworkD15.service.mapping;

import com.example.homeworkD15.controller.response.UserResponse;
import com.example.homeworkD15.entity.UserEntity;
import com.example.homeworkD15.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(UserEntity user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public UserResponse toUserResponse(UserDto dto) {
        UserResponse response = new UserResponse();
        response.setId(dto.getId());
        response.setUsername(dto.getUsername());
        return response;
    }
}
