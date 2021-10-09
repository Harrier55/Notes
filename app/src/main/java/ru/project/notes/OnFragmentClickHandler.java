package ru.project.notes;

public interface OnFragmentClickHandler {

     void onClickItemListNote(NoteEntity noteEntity );
     void onClickButtonSaveNoteEditFragment(NoteEntity noteEntity);
     void onClickButtonSaveNewListItemFragment(NoteEntity noteEntity);

}
