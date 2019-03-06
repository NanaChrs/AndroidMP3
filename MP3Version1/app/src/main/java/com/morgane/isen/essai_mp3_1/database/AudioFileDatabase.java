package com.morgane.isen.essai_mp3_1.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

@Database(entities = {AudioFile.class},version = 1)
public abstract class AudioFileDatabase  extends RoomDatabase{
    public abstract Dao dao();
}
