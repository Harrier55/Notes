package ru.project.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTV = itemView.findViewById(R.id.title_text_view);
    private TextView detailTV = itemView.findViewById(R.id.detail_text_view);

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private NoteEntity noteEntity;

    /*** Пункт 1
     * создали новый конструктор
     */
    public NoteViewHolder(ViewGroup parent, NotesAdapter.OnItemClickListener clickListener){
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(noteEntity);
            }
        });

    }
    /***Пункт 2.
     * создаем метод bind
     * в новый конструктор переносим слушатель
     * создаем переменную  private NoteEntity noteEntity;
     * и в методе bind() привязываем ее..
     */

    public void bind(NoteEntity noteEntity){
        this.noteEntity = noteEntity;
        titleTV.setText(noteEntity.getTitle());
        detailTV.setText(noteEntity.getDetail());
    }

}
