package ru.project.notes.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.project.notes.Entity.NoteEntity;
import ru.project.notes.listener.OnFragmentClickHandler;
import ru.project.notes.R;

public class NewListItemFragment extends Fragment {

    private OnFragmentClickHandler onFragmentClickHandler;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentClickHandler){
            onFragmentClickHandler =(OnFragmentClickHandler) context;
        }  else {throw new RuntimeException(context.toString()+ " must implement OnFragmentClickHandler to NewListItemFrag");}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_edit, container, false);

        EditText titleEditText = view.findViewById(R.id.title_edit_text);
        EditText detailEditText = view.findViewById(R.id.detail_edit_text);
        Button saveButton = view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(view1 -> {

            String title = titleEditText.getText().toString();
            String detail = detailEditText.getText().toString();

            NoteEntity noteEntityNew = new NoteEntity(title,detail);

            onFragmentClickHandler.onClickButtonSaveNewListItemFragment(noteEntityNew);
        });
        return view;

    }
}