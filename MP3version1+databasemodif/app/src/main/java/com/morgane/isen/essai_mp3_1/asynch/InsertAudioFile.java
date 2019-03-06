package com.morgane.isen.essai_mp3_1.asynch;

import android.os.AsyncTask;

import com.morgane.isen.essai_mp3_1.database.DatabaseHelper;
import com.morgane.isen.essai_mp3_1.interfaces.AudioChangeListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.List;

public class InsertAudioFile extends AsyncTask<AudioFile, Void, Void> {

    private AudioChangeListener mlistener;
    private AudioFile music;

    public InsertAudioFile(AudioChangeListener mlistener, AudioFile audio) {
        this.mlistener = mlistener;
        this.music = audio;
    }

    @Override
    protected Void doInBackground(AudioFile... truc) {
        DatabaseHelper.getInstance().getDatabase().insertMusic(music);
        return null;
    }

}
