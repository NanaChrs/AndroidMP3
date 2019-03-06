package com.morgane.isen.essai_mp3_1.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.List;

@android.arch.persistence.room.Dao
public interface Dao {
    @Query("SELECT * FROM AudioFile")
    List<AudioFile> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AudioFile> artists);

}
