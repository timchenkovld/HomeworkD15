package com.example.homeworkD15.controller.controllers;

import com.example.homeworkD15.service.mapping.NoteMapper;
import com.example.homeworkD15.service.mapping.UserMapper;
import com.example.homeworkD15.service.service.NoteService;
import com.example.homeworkD15.service.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Validated
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoteMapper noteMapper;

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Cookie cookie = new Cookie("userId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        request.logout();
        return new ModelAndView("notes/index");
    }
}
