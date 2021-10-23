package ru.project.notes.listener;

import android.view.MenuItem;

import ru.project.notes.Entity.NoteEntity;

public interface OnFragmentClickHandler {

     void onClickItemListNote(NoteEntity noteEntity );
     void onClickButtonSaveNoteEditFragment(NoteEntity noteEntity);
     void onClickButtonSaveNewListItemFragment(NoteEntity noteEntity);
     void onClickButtonMenuNoteWidget(MenuItem menuItem, NoteEntity noteEntity);

}
