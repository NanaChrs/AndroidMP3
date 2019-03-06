package com.morgane.isen.essai_mp3_1.database;

import android.arch.persistence.room.Room;

import com.morgane.isen.essai_mp3_1.MP3Application;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

public class DatabaseHelper {
    static DatabaseHelper instance = null;
    private AudioFileDatabase db;
    public static DatabaseHelper getInstance(){
        if(instance == null){
            instance = new DatabaseHelper();
        }
        return instance;
    }

    public Dao getDatabase() {
        return db.dao();
    }

    public DatabaseHelper(){
        db = Room.databaseBuilder(MP3Application.getContext(),AudioFileDatabase.class,"ma_bdd.db").build();
    }
}
