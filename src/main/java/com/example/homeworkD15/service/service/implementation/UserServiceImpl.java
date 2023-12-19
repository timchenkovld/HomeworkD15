package com.example.homeworkD15.service.service.implementation;

import com.example.homeworkD15.entity.UserEntity;
import com.example.homeworkD15.repository.UserRepository;
import com.example.homeworkD15.service.dto.UserDto;
import com.example.homeworkD15.service.exception.UserAlreadyExistException;
import com.example.homeworkD15.service.exception.UserIncorrectPasswordException;
import com.example.homeworkD15.service.exception.UserNotFoundException;
import com.example.homeworkD15.service.mapping.UserMapper;
import com.example.homeworkD15.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto login(String username, String password) throws UserIncorrectPasswordException {
        UserEntity userEntity = userRepository.findUserByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        if (userEntity.getPassword().equals(password)){
            return userMapper.toUserDto(userEntity);
        } else {
            throw new UserIncorrectPasswordException(username);
        }
    }
}
