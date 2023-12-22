package com.example.homeworkD15.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "title", length = 150, nullable = false)
    @Size(min = 3, max = 150)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;
}
