package ru.project.notes;

import android.view.MenuItem;
import android.view.View;

public interface OnFragmentClickHandler {

     void onClickItemListNote(NoteEntity noteEntity );
     void onClickButtonSaveNoteEditFragment(NoteEntity noteEntity);
     void onClickButtonSaveNewListItemFragment(NoteEntity noteEntity);
     void onClickButtonMenuNoteWidget(MenuItem menuItem, NoteEntity noteEntity);

}
