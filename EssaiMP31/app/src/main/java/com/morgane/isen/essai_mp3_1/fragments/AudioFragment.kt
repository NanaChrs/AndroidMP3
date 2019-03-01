package com.morgane.isen.essai_mp3_1.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.provider.SyncStateContract
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.morgane.isen.essai_mp3_1.Constants
import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer
import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer.audioFiles
import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer.mediaPlayer
import com.morgane.isen.essai_mp3_1.MP3Application
import com.morgane.isen.essai_mp3_1.R
import com.morgane.isen.essai_mp3_1.pojo.AudioFile
import kotlinx.android.synthetic.main.fragment_audio.*



class AudioFragment : Fragment(), GlobalMediaPlayer{

    private var isPaused = false;

    companion object {
        lateinit var instance : AudioFragment

            private set
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_audio, container, false)
        val name = arguments!!.getString(Constants.Audio.EXTRA_NAME)
        (view.findViewById<View>(R.id.titlebis) as TextView).text = name
        val artist = arguments!!.getString(Constants.Audio.EXTRA_ARTIST)
        (view.findViewById<View>(R.id.artistbis) as TextView).text = artist
        val album = arguments!!.getString(Constants.Audio.EXTRA_ALBUM)
        (view.findViewById<View>(R.id.albumbis) as TextView).text = album
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var newSong = true

        val PATH_TO_FILE = arguments!!.getString(Constants.Audio.EXTRA_PATH)



        playbis.setOnTouchListener {  _, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_UP -> {
                    println("bouton appuyé")
                    if (!isPaused || newSong){
                        mediaPlayer.stop()
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(PATH_TO_FILE)
                        mediaPlayer.prepare()
                        isPaused = false
                    }
                    newSong = false
                    mediaPlayer.start()
                }
            }
            true
        }

        pauseButton.setOnTouchListener {  _, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_UP -> {
                    println("bouton appuyé")
                    mediaPlayer.pause()
                    isPaused = true
                    newSong = false
                    //mediaPlayer?.seekTo(0)
                }
            }
            true
        }

        stopButton.setOnTouchListener {  _, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_UP -> {
                    println("bouton appuyé")
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    val audio = getTrackById(PATH_TO_FILE)
                    Log.d("audiotest",audio.toString())
                    if (audio!=null){
                        //Log.d("audiio", audio.i.toString())
                        mediaPlayer.setDataSource(audioFiles[audio.i+1].path)
                        mediaPlayer.prepare()
                        //Log.d("audioFilepat",audioFiles[audio.i+1].path )
                        mediaPlayer.start()
                    }
                    newSong = false
                    //mediaPlayer.seekTo(0)
                }
            }
            true
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()

        //mediaPlayer?.stop()
    }

    fun getTrackById(path: String): AudioFile?{

        for(i in 0..audioFiles.size ){
            if(audioFiles[i].path==path){
                return audioFiles[i]
            }
        }

        return null
    }

}
