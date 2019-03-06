package com.morgane.isen.essai_mp3_1.listener;

import android.widget.SeekBar;

import static com.morgane.isen.essai_mp3_1.GlobalMediaPlayer.mediaPlayer;

public class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mediaPlayer.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
