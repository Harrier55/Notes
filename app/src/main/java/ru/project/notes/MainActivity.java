package ru.project.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Application;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnFragmentClickHandler{

    private static final String TAG = "@@@@@ Main Activity";


    ActionBar actionBar;
    private  NotesRepoImpl notesRepo = new NotesRepoImpl();
    private final NotesAdapter adapter = new NotesAdapter();
    private Integer currentItemId;

    final int REQUEST_CODE_EDIT = 2;
    final int REQUEST_CODE_NEW = 1;

    MainListFragment mainListFragment = new MainListFragment();
    NoteEditFragment noteEditFragment = new NoteEditFragment();
    NewListItemFragment newListItemFragment = new NewListItemFragment();

    @Override
    public void onClickButtonSaveNoteEditFragment(NoteEntity editNoteEntity) {
        Toast.makeText(this, "6666666", Toast.LENGTH_SHORT).show();
        int idEdit= editNoteEntity.getId();
        NoteEntity noteEntityEdit = new NoteEntity(editNoteEntity.getTitle(),editNoteEntity.getDetail());
        notesRepo.updateNote(idEdit,noteEntityEdit);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainListFragment)
                .commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_fragment);

        generateTestRepo();
//        initRecycler();
        launcherFragment(mainListFragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void launcherFragment(Fragment classFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, classFragment)
                .commit();

    }

    @Override
    public void onClickItemListNote(NoteEntity noteEntity) {

        NoteEditFragment noteEditFragment = new NoteEditFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("one",noteEntity);
        noteEditFragment.setArguments(bundle);

        launcherFragment(noteEditFragment);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            launcherFragment(newListItemFragment);
        return super.onOptionsItemSelected(item);
    }



//    private void initRecycler() {
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
////        adapter.setOnItemClickListener(this::onItemClick);
//        adapter.setData(notesRepo.getNotes());
//    }




    /***
     * клик на элемент меню ActionBar
     *
     *
     */

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        openNewNote();
//        return super.onOptionsItemSelected(item);
//    }


    /***
     * клик на выбор элемента списка RecyclerView
     *
     */
//    private void onItemClick(NoteEntity item) {
//
//        openNoteScreen(item);
//    }

    /***
     *  Старт новой Активити NoteEditActivity
     *
     */
//    private void openNoteScreen(NoteEntity item) {
//        Intent intent = new Intent(this, NoteEditActivity.class);
//        currentItemId = item.getId();
//        intent.putExtra("document", item);
//        startActivityForResult(intent, REQUEST_CODE_EDIT);
//    }

//    private void openNewNote() {
//        Intent intent = new Intent(this, NoteEditActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_NEW);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK){
//        NoteEntity noteEntity = data.getParcelableExtra("return");
//
//            if (requestCode == REQUEST_CODE_EDIT){
//
//                    int id = noteEntity.getId(); // почему то она всегда равна 2 ???
//
//                    notesRepo.updateNote(currentItemId, noteEntity);}
//
//            else if (requestCode == REQUEST_CODE_NEW){
//
//                notesRepo.createNote(noteEntity);
//            }
//        }
//
//        initRecycler();
//    }


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