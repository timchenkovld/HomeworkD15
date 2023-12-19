package com.example.homeworkD15.service.service;

import com.example.homeworkD15.service.dto.UserDto;
import com.example.homeworkD15.service.exception.UserAlreadyExistException;
import com.example.homeworkD15.service.exception.UserIncorrectPasswordException;
import com.example.homeworkD15.service.exception.UserNotFoundException;

public interface UserService {
    UserDto login (String username, String password) throws UserNotFoundException, UserIncorrectPasswordException;
//    UserDto registration(String username, String password) throws UserAlreadyExistException;
//
//    UserDto updateUser(Long userId, String oldUsername, String oldPassword,
//                       String newUsername, String newPassword) throws UserNotFoundException,
//                        UserIncorrectPasswordException;
}
