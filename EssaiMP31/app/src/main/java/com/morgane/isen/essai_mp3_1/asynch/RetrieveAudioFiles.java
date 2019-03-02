package com.morgane.isen.essai_mp3_1.asynch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer;
import com.morgane.isen.essai_mp3_1.MP3Activity;
import com.morgane.isen.essai_mp3_1.MP3Application;
import com.morgane.isen.essai_mp3_1.MainActivity;
import com.morgane.isen.essai_mp3_1.fragments.AudioFragment;
import com.morgane.isen.essai_mp3_1.interfaces.AudioChangeListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;

public class RetrieveAudioFiles extends AsyncTask<String, Void, List<AudioFile>> implements GlobalMediaPlayer {

    private AudioChangeListener mlistener;

    public RetrieveAudioFiles(AudioChangeListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    protected List<AudioFile> doInBackground(String... strings) {
        List<AudioFile> allAudioFiles = new ArrayList<>();

        Context context = MP3Application.getContext();
        //Log.e("hello", context.toString());
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.AudioColumns.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int i =0;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                AudioFile audioFile = new AudioFile();
                audioFile.setName(cursor.getString(0));
                audioFile.setArtist(cursor.getString(1));
                audioFile.setAlbum(cursor.getString(2));
                audioFile.setPath(cursor.getString(3));
                audioFile.setI(i);

                Log.e("Name :" + audioFile.getName(), " Artist :" + audioFile.getArtist());
                Log.e(" Album :" + audioFile.getAlbum(), "Path: " + audioFile.getPath());

                allAudioFiles.add(audioFile);
                i++;
            }
            Collections.sort(allAudioFiles);
            cursor.close();
            for(int j=0;j<allAudioFiles.size();j++) {
                audioFiles.add(allAudioFiles.get(j));
                audioFiles.get(j).setI(j);
            }
        }
        return allAudioFiles;
    }

    @Override
    protected void onPostExecute(List<AudioFile> audioFileList) {
        super.onPostExecute(audioFileList);
        mlistener.onTweetRetrieved(audioFileList);
    }

}