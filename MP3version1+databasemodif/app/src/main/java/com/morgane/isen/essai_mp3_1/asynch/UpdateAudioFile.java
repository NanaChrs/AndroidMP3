package com.morgane.isen.essai_mp3_1.asynch;

import android.os.AsyncTask;

import com.morgane.isen.essai_mp3_1.database.DatabaseHelper;
import com.morgane.isen.essai_mp3_1.interfaces.AudioChangeListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

public class UpdateAudioFile extends AsyncTask<AudioFile, Void, Void> {

    private AudioChangeListener mlistener;
    private AudioFile music;
    private long id;

    public UpdateAudioFile(AudioChangeListener mlistener,AudioFile audio, long idAudioInBDD) {
        this.mlistener = mlistener;
        this.music = audio;
        this.id = idAudioInBDD;
    }

    @Override
    protected Void doInBackground(AudioFile... truc) {
        DatabaseHelper.getInstance().getDatabase().updateMusic(id, music.getName(), music.getArtist(), music.getAlbum(), music.getPath());
        return null;
    }
}
