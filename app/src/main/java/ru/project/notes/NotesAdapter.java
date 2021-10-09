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
    private OnItemClickListener clickListener;

    public void setData(List<NoteEntity> data){
        this.data = data;
        notifyDataSetChanged();
    }

    /*** Улучшение адаптера. Пункт 1. Урок 6 ,время 2,29
     * Преобразовали адаптер, вынесли логику в NoteViewHolder, чтобы этот класс ничего не знал
     *  перенесли создание View view = LayoutInflater....
     * было
     * View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
     * return new NoteViewHolder(view);
     *
     *
     *   таким образом задействовали parent родительского класса
     *   перенесли сосдание элемента внутрь RecyclerView.ViewHolder
     *   теперь адаптер не знает, какой у ViewHolder там Layout
     */
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(parent,clickListener);
    }

    /*** Пункт 2. Вынносим логику из onBindViewHolder
     * было
     * public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
     *
     *         NoteEntity note = getItem(position);
     *         holder.itemView.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View view) {
     *                 clickListener.onItemClick(note);
     *             }
     *         });
     *         holder.titleTV.setText(note.getTitle());
     *         holder.detailTV.setText(note.getDetail());
     * создаем метод   holder.bind(note)  и вырезаем все что ниже,
     * метод реализуем в Холдере
     *
     * А слушатель holder.itemView.setOnClickListener  переносим в
     * новый конструктор
     * по идее его можно расположить в етодк bind или в конструкторе
     * но bind будет постоянно вызываться, а он тяжелый
     *
     * попутно заводим переменную private NoteEntity noteEntity - строка 30 холдера
     * добавляем слушатель
     *  строка 40   return new NoteViewHolder(parent,clickListener);
     *
     */

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteEntity note = getItem(position);
        holder.bind(note);
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
