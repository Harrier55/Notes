package ru.project.notes.domain;

import androidx.annotation.Nullable;

import java.util.List;

import ru.project.notes.Entity.NoteEntity;

public interface NotesRepo {

    List<NoteEntity> getNotes();

    @Nullable
    Integer createNote(NoteEntity noteEntity);
    boolean deleteNote(int id);
    boolean updateNote(int id, NoteEntity noteEntity);

}
