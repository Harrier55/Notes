package ru.project.notes;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NotesRepoImpl implements NotesRepo{



    private static final String TAG ="@@@@ NoteRepoImplements";

    ArrayList<NoteEntity> cache = new ArrayList<>();
    private int counter = 0;

    @Override
    public List<NoteEntity> getNotes() {
        return new ArrayList<>(cache);
    }

    @Nullable
    @Override
    public Integer createNote(NoteEntity noteEntity) {
        int newId = counter++;
        noteEntity.setId(newId);
        cache.add(noteEntity);
        Log.d(TAG, "Id noteEntity create= [" + newId + "]");
        return newId;
    }

    @Override
    public boolean deleteNote(int id) {
       for (int i=0; i< cache.size(); i++ ){
           if (cache.get(i).getId() == id){
               cache.remove(i);
               return true;
           }
       }
        return false;
    }

    @Override
    public boolean updateNote(int id, NoteEntity noteEntity) {
        deleteNote(id);
        noteEntity.setId(id);
        cache.add(id,noteEntity);
        return true;
    }
}
