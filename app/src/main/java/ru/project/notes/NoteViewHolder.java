package ru.project.notes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public TextView titleTV = itemView.findViewById(R.id.title_text_view);
    public TextView detailTV = itemView.findViewById(R.id.detail_text_view);
}
