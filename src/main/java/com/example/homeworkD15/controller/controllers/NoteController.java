package com.example.homeworkD15.controller.controllers;

import com.example.homeworkD15.service.dto.NoteDto;
import com.example.homeworkD15.service.exception.NoteNotFoundException;
import com.example.homeworkD15.service.mapping.NoteMapper;
import com.example.homeworkD15.service.service.NoteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Validated
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @GetMapping("/list")
    public ModelAndView noteList() {
        ModelAndView result = new ModelAndView("note/allNotes");
        result.addObject("notes", noteMapper.toNoteResponses(noteService.listAll()));
        return result;
    }

    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@NotNull @RequestParam(value = "id") String id) throws NoteNotFoundException {
        UUID uuid = UUID.fromString(id);
        ModelAndView result = new ModelAndView("note/updateNotes");
        result.addObject("note", noteMapper.toNoteResponse(noteService.getById(uuid)));
        return result;
    }

    @PostMapping("/create")
    public ModelAndView createNote(
            @RequestParam(value = "title") @Size(min = 3, max = 150) String title,
            @RequestParam(value = "content") @NotBlank String content) {
        NoteDto dto = new NoteDto();
        dto.setTitle(title);
        dto.setContent(content);
        noteService.add(dto);
        return noteList();
    }

    @PostMapping("/edit")
    public ModelAndView updateNote(@NotNull @RequestParam(value = "id") String id,
            @Size(min = 3, max = 250) @RequestParam(value = "title") String title,
            @NotEmpty @RequestParam(value = "content") String content) throws NoteNotFoundException {
        NoteDto dto = new NoteDto();
        dto.setId(UUID.fromString(id));
        dto.setTitle(title);
        dto.setContent(content);
        noteService.update(dto);
        return noteList();
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@NotNull @RequestParam("id") UUID id) throws NoteNotFoundException {
        noteService.deleteById(id);
        return noteList();
    }



    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ModelAndView("redirect:/login");
    }
}
