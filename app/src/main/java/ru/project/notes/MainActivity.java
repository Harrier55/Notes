package ru.project.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "@@@@@ Main Activity";
    ActionBar actionBar;
    private final NotesRepo notesRepo = new NotesRepoImpl();
    private final NotesAdapter adapter = new NotesAdapter();
    private Integer currentItemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateTestRepo();
        initRecycler();

    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.setData(notesRepo.getNotes());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /***
     * клик на элемент меню ActionBar
     *
     *
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        openNewNote();
        return super.onOptionsItemSelected(item);
    }


    /***
     * клик на выбор элемента списка RecyclerView
     *
     */
    private void onItemClick(NoteEntity item) {

        openNoteScreen(item);
    }

    /***
     *  Старт новой Активити NoteEditActivity
     *
     */
    private void openNoteScreen(NoteEntity item) {
        Intent intent = new Intent(this, NoteEditActivity.class);
        currentItemId = item.getId();
        intent.putExtra("document", item);
        startActivityForResult(intent, 1);
    }

    private void openNewNote() {
        Intent intent = new Intent(this, NoteEditActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
        NoteEntity noteEntity = data.getParcelableExtra("return");
        notesRepo.updateNote(0, noteEntity);
//        notesRepo.updateNote(currentItemId, noteEntity);
        }

        initRecycler();
    }


    private void generateTestRepo() {
        notesRepo.createNote(new NoteEntity("Заметка 1", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 2", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 3", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 4", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 5", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 6", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 7", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 8", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 9", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
        notesRepo.createNote(new NoteEntity("Заметка 10", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));

    }
}