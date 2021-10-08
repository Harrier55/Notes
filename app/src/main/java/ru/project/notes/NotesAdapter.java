package ru.project.notes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private static final String TAG = "@@@@@ NoteAdapter";


    private List<NoteEntity> data = new ArrayList<>();
    OnItemClickListener clickListener;

    public void setData(List<NoteEntity> data){

        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
//        Log.d(TAG, "position = [" + position + "]");
        NoteEntity note = getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(note);
            }
        });
        holder.titleTV.setText(note.getTitle());
        holder.detailTV.setText(note.getDetail());

    }

    private NoteEntity getItem(int position){
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }

    interface OnItemClickListener{
        void onItemClick(NoteEntity item);
    }
}
