package ru.project.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ActionBar actionBar;
    private NotesRepo notesRepo = new NotesRepoImpl();
    private NotesAdapter adapter = new NotesAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateTestRepo();
        initRecycler();

    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);

        adapter.setData(notesRepo.getNotes());
//        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(NoteEntity item) {
//                onItemClick(item);
//            }
//        });

    }

    private void onItemClick(NoteEntity item) {
        openNoteScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        item.getItemId();
//        Toast.makeText(this, "id" + item.getItemId(), Toast.LENGTH_SHORT).show();
        openNoteScreen();
        return super.onOptionsItemSelected(item);

    }

    private void openNoteScreen() {
        Intent intent = new Intent(this, NoteEditActivity.class);
        startActivity(intent);

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