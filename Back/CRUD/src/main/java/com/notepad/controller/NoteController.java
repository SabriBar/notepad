package com.notepad.controller;
import com.notepad.entity.Note;
import com.notepad.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/active")
    public List<Note> getActiveNotes() {
        return noteService.getActiveNotes();
    }

    @GetMapping("/archived")
    public List<Note> getArchivedNotes() {
        return noteService.getArchivedNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        return note.orElse(null);
    }

    @PostMapping("/note")
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @PutMapping("/{id}")
    public Note editNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        return noteService.editNote(id, updatedNote);
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }

    @PutMapping("/{id}/archive")
    public void archiveNoteById(@PathVariable Long id, @RequestBody ArchiveRequest request) {
        noteService.archiveNoteById(id, request.isArchived());
    }
}

