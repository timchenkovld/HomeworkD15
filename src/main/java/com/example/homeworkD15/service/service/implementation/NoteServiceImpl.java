package com.example.homeworkD15.service.service.implementation;

import com.example.homeworkD15.entity.NoteEntity;
import com.example.homeworkD15.entity.UserEntity;
import com.example.homeworkD15.repository.NoteRepository;
import com.example.homeworkD15.service.dto.NoteDto;
import com.example.homeworkD15.service.exception.NoteNotFoundException;
import com.example.homeworkD15.service.mapping.NoteMapper;
import com.example.homeworkD15.service.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Override
    public List<NoteDto> listAll() {
        return noteMapper.toNoteDtos(noteRepository.findAll());
    }

    @Override
    @Transactional
    public NoteDto add(NoteDto note) {
        NoteEntity entity = noteMapper.toNoteEntity(note);
        entity.setId(null);
        entity.setUser(new UserEntity(note.getUserId()));
        return noteMapper.toNoteDto(noteRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteById(UUID id, UUID userId) throws NoteNotFoundException {
        getById(id);
        noteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(NoteDto note) throws NoteNotFoundException {
        if (Objects.isNull(note.getId())) {
            throw new NoteNotFoundException();
        }
        getById(note.getId());
        noteRepository.save(noteMapper.toNoteEntity(note));
    }

    @Override
    public NoteDto getById(UUID id) throws NoteNotFoundException {
        Optional<NoteEntity> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            return noteMapper.toNoteDto(optionalNote.get());
        } else {
            throw new NoteNotFoundException(id);
        }
    }
}
