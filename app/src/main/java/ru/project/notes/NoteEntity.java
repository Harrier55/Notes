package ru.project.notes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public class NoteEntity implements Parcelable {


    @Nullable private  Integer id;
    private String title;
    private String description;


    public NoteEntity( String title, String detail) {
//        this.id = id;
        this.title = title;
        this.description = detail;
    }

    public NoteEntity( Integer id,String title, String detail) {
        this.id = id;
        this.title = title;
        this.description = detail;
    }

    protected NoteEntity(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
    }




    @Nullable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{title,description});

    }

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel in) {
            return new NoteEntity(in);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };
}
