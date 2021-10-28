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


public class NoteEditFragment extends Fragment {

    NoteEntity noteEntity;
    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;
    private OnFragmentClickHandler onFragmentClickHandler;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentClickHandler) {
            onFragmentClickHandler = (OnFragmentClickHandler) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentClickHandler to NoteEditFrag");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteEntity = getArguments().getParcelable("one");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_edit, container, false);

        initView(view);

        titleEditText.setText(noteEntity.getTitle());
        detailEditText.setText(noteEntity.getDetail());

        saveButton.setOnClickListener(view1 -> {
            String title = titleEditText.getText().toString();
            String detail = detailEditText.getText().toString();
            NoteEntity noteEntityNew = new NoteEntity(noteEntity.getId(), title, detail);
            onFragmentClickHandler.onClickButtonSaveNoteEditFragment(noteEntityNew);
        });
        return view;
    }

    public void initView(View view) {
        titleEditText = view.findViewById(R.id.title_edit_text);
        detailEditText = view.findViewById(R.id.detail_edit_text);
        saveButton = view.findViewById(R.id.save_button);
    }
}