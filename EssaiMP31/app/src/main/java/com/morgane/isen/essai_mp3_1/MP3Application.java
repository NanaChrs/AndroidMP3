package com.morgane.isen.essai_mp3_1;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

public class MP3Application extends Application {
    private static Context sContext;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void onCreate(){
        super.onCreate();
        // Keep a reference to the application context
        sContext = getApplicationContext();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }

}
