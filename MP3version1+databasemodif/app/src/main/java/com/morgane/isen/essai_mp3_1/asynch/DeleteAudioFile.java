package com.morgane.isen.essai_mp3_1.asynch;

import android.os.AsyncTask;

import com.morgane.isen.essai_mp3_1.database.DatabaseHelper;
import com.morgane.isen.essai_mp3_1.interfaces.AudioChangeListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

public class DeleteAudioFile extends AsyncTask<AudioFile, Void, Void> {


    private AudioChangeListener mlistener;
    private AudioFile music;

    public DeleteAudioFile(AudioChangeListener mlistener, AudioFile audio) {
        this.mlistener = mlistener;
        this.music = audio;
    }

    @Override
    protected Void doInBackground(AudioFile... audioFiles) {
        DatabaseHelper.getInstance().getDatabase().deleteMusic(music.getI());
        return null;
    }
}

