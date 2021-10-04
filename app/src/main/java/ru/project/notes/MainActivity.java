package ru.project.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "@@@@@ Main Activity";
    ActionBar actionBar;
    private final NotesRepo notesRepo = new NotesRepoImpl();
    private final NotesAdapter adapter = new NotesAdapter();
    private Integer currentItemId;

    final int REQUEST_CODE_EDIT = 2;
    final int REQUEST_CODE_NEW = 1;

    MainListFragment mainListFragment = new MainListFragment();
    EditListItemFragment editListItemFragment = new EditListItemFragment();
    NewListItemFragment newListItemFragment = new NewListItemFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_fragment);

        generateTestRepo();
//        initRecycler();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, mainListFragment)
                .commit();


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
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    private void openNewNote() {
        Intent intent = new Intent(this, NoteEditActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
        NoteEntity noteEntity = data.getParcelableExtra("return");

            if (requestCode == REQUEST_CODE_EDIT){

                    int id = noteEntity.getId(); // почему то она всегда равна 2 ???

                    notesRepo.updateNote(currentItemId, noteEntity);}

            else if (requestCode == REQUEST_CODE_NEW){

                notesRepo.createNote(noteEntity);
            }
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