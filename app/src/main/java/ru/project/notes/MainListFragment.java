package ru.project.notes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.logging.LogManager;


public class MainListFragment extends Fragment {

    private static final String TAG = "@@@@@ Main Fragment";
    private final static NotesRepoImpl notesRepo = new NotesRepoImpl();
    private final NotesAdapter adapter = new NotesAdapter();
    private OnFragmentClickHandler onFragmentClickHandler;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentClickHandler){
            onFragmentClickHandler = (OnFragmentClickHandler) context;
        }else {throw new RuntimeException(context.toString()+ " must be implement OnFragment_DataListener");}

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesRepo.getNotes();
//        Log.d(TAG, "notesRepo = [" + notesRepo.getNotes() + "]");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_main_list, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClickRecyclerView);
        adapter.setData(notesRepo.getNotes());
    }

    private void onItemClickRecyclerView(NoteEntity noteEntity) {
        // todo
        int ee = noteEntity.getId();
//        Log.d(TAG, "onItemClick() called with: itemId = [" + ee + "]");
//        Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
          onFragmentClickHandler.onClickItemListNote(noteEntity);

    }
}