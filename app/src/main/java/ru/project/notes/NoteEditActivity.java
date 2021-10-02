package ru.project.notes;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;


public class NoteEditActivity extends AppCompatActivity {

    private static final String TAG = "@@@@@ NoteEditActivity";

    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        initView();
        getIntentFromMainActivity();

        saveButton.setOnClickListener(view -> onClickButtonSave());
    }


    private void initView() {
        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.detail_edit_text);
        saveButton = findViewById(R.id.save_button);
    }

    private void getIntentFromMainActivity() {
        NoteEntity noteEntity = getIntent().getParcelableExtra("document");

        if (noteEntity != null) {
            titleEditText.setText(noteEntity.getTitle());
            detailEditText.setText(noteEntity.getDetail());
        }
    }

    private void returnNoteEntityForMainActivity(NoteEntity noteEntity){
        Intent intent = new Intent();
        intent.putExtra("return", noteEntity);
        setResult(RESULT_OK, intent);
        finish();
    }

    private  void onClickButtonSave (){
        String title = titleEditText.getText().toString();
        String detail = detailEditText.getText().toString();

        if (title != null && detail != null) {
            NoteEntity noteEntity = new NoteEntity(title,detail);
            returnNoteEntityForMainActivity(noteEntity);
        }
    }
}
