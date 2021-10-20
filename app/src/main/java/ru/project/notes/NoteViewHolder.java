package ru.project.notes;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder  {

    private TextView titleTV = itemView.findViewById(R.id.title_text_view);
    private TextView detailTV = itemView.findViewById(R.id.detail_text_view);
    private Button menuItemNoteWidgetButton = itemView.findViewById(R.id.menu_item_note_widget_button);

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private NoteEntity noteEntity;
    private OnItemClickListenerPopUpMenu onItemClickListenerPopUpMenu;



    /*** Пункт 1
     * создали новый конструктор
     */
    public NoteViewHolder(ViewGroup parent, NotesAdapter.OnItemClickListener clickListener, OnItemClickListenerPopUpMenu onItemClickListenerPopUpMenu){
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false));
        this.onItemClickListenerPopUpMenu = onItemClickListenerPopUpMenu;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(titleTV.getContext(), noteEntity.getTitle(), Toast.LENGTH_SHORT).show();
                noteEntity.getTitle();
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

        menuItemNoteWidgetButton.setOnClickListener((View view) -> {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.menu_pop_up);
            popupMenu.setOnMenuItemClickListener((MenuItem menuItem) -> {
                onItemClickListenerPopUpMenu.onClickItemPopUpMenu(menuItem,noteEntity);
                return true;
            });
            popupMenu.show();
        });
    }
}
