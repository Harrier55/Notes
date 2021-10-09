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
//   private final NotesAdapter adapter = new NotesAdapter();

    MainListFragment mainListFragment = new MainListFragment();
    NewListItemFragment newListItemFragment = new NewListItemFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_fragment);

        generateTestRepo();
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
    public void onClickButtonSaveNoteEditFragment(NoteEntity editNoteEntity) {
        int idEdit= editNoteEntity.getId();
        NoteEntity noteEntityEdit = new NoteEntity(editNoteEntity.getTitle(),editNoteEntity.getDetail());
        notesRepo.updateNote(idEdit,noteEntityEdit);

        launcherFragment(mainListFragment);
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
    public void onClickButtonSaveNewListItemFragment(NoteEntity noteEntity) {
        notesRepo.createNote(noteEntity);
        launcherFragment(mainListFragment);
    }

    /***
     * клик на элемент меню ActionBar
     *
     *
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            launcherFragment(newListItemFragment);
        return super.onOptionsItemSelected(item);
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