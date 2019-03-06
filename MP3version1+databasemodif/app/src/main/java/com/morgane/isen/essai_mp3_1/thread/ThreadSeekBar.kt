package com.morgane.isen.essai_mp3_1.thread

import android.widget.SeekBar
import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer

class ThreadSeekBar(var seekBarAudio: SeekBar, var isPaused : Boolean) : Thread() {

    init{
        run()
        super.start()
    }

    override fun run() {
        if (!isPaused) {
            if (seekBarAudio!= null) {
                seekBarAudio.setProgress(GlobalMediaPlayer.mediaPlayer.getCurrentPosition())
            }

            seekBarAudio.postDelayed(this, 1000)

        }
    }
}
