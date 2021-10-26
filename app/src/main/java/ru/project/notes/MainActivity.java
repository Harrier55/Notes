package ru.project.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.annotation.SuppressLint;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ru.project.MyApp;
import ru.project.notes.Entity.NoteEntity;
import ru.project.notes.domain.NotesRepoImpl;
import ru.project.notes.listener.OnFragmentClickHandler;
import ru.project.notes.ui.NewListItemFragment;
import ru.project.notes.ui.NoteEditFragment;
import ru.project.notes.ui.ProfileFragment;
import ru.project.notes.ui.SettingFragment;

public class MainActivity extends AppCompatActivity implements OnFragmentClickHandler {

    private static final String TAG = "@@@@@ Main Activity";

//    private final NotesRepoImpl notesRepo = new NotesRepoImpl();

    NotesRepoImpl notesRepo;

    MainListFragment mainListFragment = new MainListFragment();
    NewListItemFragment newListItemFragment = new NewListItemFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SettingFragment settingFragment = new SettingFragment();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_fragment);

        initNotesRepoFromMyApp();

//        notesRepo = ((MyApp)getApplicationContext()).getMyAppNotesRepo();

        if (savedInstanceState == null) {
            ((MyApp)getApplicationContext()).generateTestRepo();
//            generateTestRepo();
            launcherFragmentWithAddToBackStack(mainListFragment);
        }
        initBottomNavigation();
    }

    private void initNotesRepoFromMyApp(){
        notesRepo = ((MyApp)getApplicationContext()).getMyAppNotesRepo();
    }


    private void initBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_button_navi_profile:
                        item.setChecked(true);
                        launchFragmentFromBottomNavigation(profileFragment);
                        setActionBarCustom(R.string.profile);
                        break;
                    case R.id.menu_item_button_navi_setting:
                        item.setChecked(true);
                        launchFragmentFromBottomNavigation(settingFragment);
                        setActionBarCustom(R.string.setting);
                        break;
                    default:
                        item.setChecked(true);
                        launchFragmentFromBottomNavigation(mainListFragment);
                        setActionBarMain();
                }
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClickButtonSaveNoteEditFragment(NoteEntity editNoteEntity) {
        editNote(editNoteEntity);
        setActionBarMain();
        launcherFragmentWithAddToBackStack(mainListFragment);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClickItemListNote(NoteEntity noteEntity) {
        NoteEditFragment noteEditFragment = new NoteEditFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("one", noteEntity);
        noteEditFragment.setArguments(bundle);
        setActionBarCustom(R.string.edit_note);
        launcherFragmentWithAddToBackStack(noteEditFragment);
    }

    @Override
    public void onClickButtonSaveNewListItemFragment(NoteEntity noteEntity) {
        createNewNote(noteEntity);
        setActionBarMain();
        launcherFragmentWithAddToBackStack(mainListFragment);
    }

    @Override
    public void onClickButtonMenuNoteWidget(MenuItem menuItem, NoteEntity noteEntity) {
        switch (menuItem.getItemId()) {
            case R.id.menu_popup_delete: deleteNote(noteEntity);
                break;
            case R.id.menu_popup_replace://todo
                break;
        }
    }

    /***
     * клик на элемент меню ActionBar
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setActionBarCustom(R.string.new_note);
        launcherFragmentWithAddToBackStack(newListItemFragment);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() != 0) {
            setActionBarMain();
            fragmentManager.popBackStack("myFrag",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            showClosingDialog();
//            super.onBackPressed();
        }
    }

    public void showClosingDialog(){
        new AlertDialog.Builder(this)
                .setView(R.layout.item_dialog)
                .setPositiveButton("Ухожу", (dialogInterface, i) -> {
                    Toast.makeText(this, "Пока...пока", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Остаюсь",null)
                .show();
    }

    public void createNewNote(NoteEntity noteEntity){
        notesRepo.createNote(noteEntity);
    }

    public void editNote(NoteEntity editNoteEntity){
        int idEdit = editNoteEntity.getId();
        NoteEntity noteEntityEdit = new NoteEntity(editNoteEntity.getTitle(), editNoteEntity.getDetail());
        notesRepo.updateNote(idEdit, noteEntityEdit);
        mainListFragment.updateRecyclerView();
    }

    void deleteNote(NoteEntity noteEntity){
        notesRepo.deleteNote(noteEntity.getId());
        mainListFragment.updateRecyclerView();
        Toast.makeText(this, "Удалена " + noteEntity.getTitle(), Toast.LENGTH_SHORT).show();
    }

    void launcherFragmentWithAddToBackStack(Fragment classFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (classFragment != mainListFragment) {

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, classFragment)
                    .addToBackStack("myFrag")
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, classFragment)
                    .commit();
        }
    }

    public void launchFragmentFromBottomNavigation(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    void setActionBarCustom(int titleFragment) {
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(titleFragment);
        View menuItem = findViewById(R.id.addNote);
        menuItem.setVisibility(View.INVISIBLE);
    }

    void setActionBarMain() {
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        View menuItem = findViewById(R.id.addNote);
        menuItem.setVisibility(View.VISIBLE);
    }


//    private void generateTestRepo() {
//        notesRepo.createNote(new NoteEntity("Заметка 1", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 2", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 3", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 4", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 5", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 6", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 7", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 8", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 9", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//        notesRepo.createNote(new NoteEntity("Заметка 10", "Миновало лето,Осень наступила.На полях и в рощах Пусто и уныло."));
//    }
}