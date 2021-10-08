package ru.project.notes;

import androidx.annotation.Nullable;

import java.util.List;

public interface NotesRepo {

    List<NoteEntity> getNotes();

    @Nullable
    Integer createNote(NoteEntity noteEntity);
    boolean deleteNote(int id);
    boolean updateNote(int id, NoteEntity noteEntity);

}
