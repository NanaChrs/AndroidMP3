package com.morgane.isen.essai_mp3_1.asynch;

import android.os.AsyncTask;

import com.morgane.isen.essai_mp3_1.database.DatabaseHelper;
import com.morgane.isen.essai_mp3_1.interfaces.AudioChangeListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.List;

public class RetrieveAudioFilesFromDatabase  extends AsyncTask<String, Void, List<AudioFile>> {
    private AudioChangeListener mlistener;
    public RetrieveAudioFilesFromDatabase(AudioChangeListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    protected List<AudioFile> doInBackground(String... login) {
        return DatabaseHelper.getInstance().getDatabase().getAll();
    }


    @Override
    protected void onPostExecute(List<AudioFile> audioFileList) {
        super.onPostExecute(audioFileList);
        mlistener.onTweetRetrieved(audioFileList);
        RetrieveAudioFiles networkAsynctask = new RetrieveAudioFiles(mlistener);
        networkAsynctask.execute();
    }

}

