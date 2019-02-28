package com.morgane.isen.essai_mp3_1.asynch;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import com.morgane.isen.essai_mp3_1.MP3Application;
import com.morgane.isen.essai_mp3_1.interfaces.AudioChangeListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.ArrayList;
import java.util.List;

public class RetrieveAudioFiles extends AsyncTask<String, Void, List<AudioFile>> {

    private AudioChangeListener mlistener;
    public RetrieveAudioFiles(AudioChangeListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    protected List<AudioFile> doInBackground(String... strings) {
        List<AudioFile> allAudioFiles = new ArrayList<>();

        Context context = MP3Application.getContext();
        Log.e("hello",context.toString());
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                AudioFile audioFile = new AudioFile();
                audioFile.setName( cursor.getString(0));
                audioFile.setArtist( cursor.getString(1));
                audioFile.setAlbum( cursor.getString(2));

                Log.e("Name :" + audioFile.getName(), " Artist :" + audioFile.getArtist());
                Log.e(" Album :" + audioFile.getAlbum(), "hello");

                allAudioFiles.add(audioFile);
            }
            cursor.close();
        }

        return allAudioFiles;
    }

    @Override
    protected void onPostExecute(List<AudioFile> audioFileList) {
        super.onPostExecute(audioFileList);
        mlistener.onTweetRetrieved(audioFileList);
    }

}
