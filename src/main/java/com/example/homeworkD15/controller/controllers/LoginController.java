package com.example.homeworkD15.controller.controllers;

import com.example.homeworkD15.service.dto.UserDto;
import com.example.homeworkD15.service.exception.UserIncorrectPasswordException;
import com.example.homeworkD15.service.exception.UserNotFoundException;
import com.example.homeworkD15.service.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public RedirectView home(HttpServletResponse response) throws UserNotFoundException, UserIncorrectPasswordException {
        UserDto user = userService.login("user", "default");
        Cookie cookie = new Cookie("userId", user.getId().toString());
        cookie.setPath("/note");
        response.addCookie(cookie);
        return new RedirectView("/note/list");
    }
}
