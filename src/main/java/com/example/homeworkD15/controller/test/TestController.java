package com.example.homeworkD15.controller.test;

import com.example.homeworkD15.service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/test")
    public ModelAndView testPage() {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("message", "Hello, World!");
        return modelAndView;
    }
}
