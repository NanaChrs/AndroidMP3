package com.morgane.isen.essai_mp3_1.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.sqlite.SQLiteOpenHelper;

import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.List;

@android.arch.persistence.room.Dao
public interface Dao {
    @Query("SELECT * FROM AudioFile")
    List<AudioFile> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AudioFile> artists);

    @Insert
    long insertMusic(AudioFile music);
/*
    @Update
    int updateMusic(AudioFile music, long musicId);
*/

    @Query("UPDATE AudioFile SET name = :nomMusic, artist = :artistMusic, album = :albumMusic, path = :path  WHERE i = :musicId")
    void updateMusic(long musicId, String nomMusic, String artistMusic, String albumMusic, String path);


    @Query("DELETE FROM AudioFile WHERE i = :musicId")
    void deleteMusic(long musicId);

}
