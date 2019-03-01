package com.morgane.isen.essai_mp3_1;

import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.ArrayList;

public interface GlobalMediaPlayer{
    MediaPlayer mediaPlayer= new MediaPlayer();
    ArrayList<AudioFile> audioFiles = new ArrayList<>();
    //int getTrackId(String nom);
}
