package com.example.homeworkD15.service.dto;

import com.example.homeworkD15.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private UUID id;
    private UUID userId;
    private String title;
    private String content;
}
