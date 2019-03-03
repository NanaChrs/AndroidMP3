package com.morgane.isen.essai_mp3_1.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.provider.SyncStateContract
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import com.morgane.isen.essai_mp3_1.*

import com.morgane.isen.essai_mp3_1.listener.SeekBarChangeListener
import com.morgane.isen.essai_mp3_1.pojo.AudioFile
import kotlinx.android.synthetic.main.fragment_audio.*
import kotlinx.android.synthetic.main.fragment_audios.*
import java.util.*
import android.support.v4.os.HandlerCompat.postDelayed
import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer.*
import java.lang.reflect.Array.getLength
import android.content.res.AssetFileDescriptor
import com.morgane.isen.essai_mp3_1.MainActivity
import android.support.v4.content.ContextCompat
import com.morgane.isen.essai_mp3_1.thread.ThreadSeekBar


class AudioFragment : Fragment(), GlobalMediaPlayer, MediaPlayer.OnPreparedListener{

    override fun onPrepared(mp: MediaPlayer?) {
        seekBarAudio.max=mediaPlayer.duration
        seekBarAudio.postDelayed(thread,1000)
    }

    private var thread : ThreadSeekBar? = null
    private var isPaused = false
    var seekBarAudio = SeekBar(MP3Application.getContext())

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
        seekBarAudio= view.findViewById(R.id.seekBar) as SeekBar
        progressMusic()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var newSong = true

        if( thread== null){
            thread = ThreadSeekBar(seekBarAudio,isPaused)
        }

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
                    if( thread== null){
                        thread = ThreadSeekBar(seekBarAudio,isPaused)
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
                    thread=null
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
                        refresh(audioFiles[(audio.i)+1])
                    }
                    thread= null
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

    fun refresh(audioFile: AudioFile?) {
        val fragment = AudioFragment()
        val bundle = Bundle()
        bundle.putString(Constants.Audio.EXTRA_NAME, audioFile?.name)
        bundle.putString(Constants.Audio.EXTRA_ARTIST, audioFile?.artist)
        bundle.putString(Constants.Audio.EXTRA_ALBUM, audioFile?.album)
        bundle.putString(Constants.Audio.EXTRA_PATH, audioFile?.path)
        fragment.arguments = bundle

        Log.d("fragmentonview", fragment.arguments.toString())

        val fm = fragmentManager
        val tx = fm!!.beginTransaction()
        tx.replace(R.id.container2, fragment)
        //tx.addToBackStack(null)
        tx.commit()

        progressMusic()

        //getSupportFragmentManager().inTransaction {
        //    add(R.id.container2, fragment)
        //}

    }

    fun progressMusic(){
        mediaPlayer.setOnPreparedListener(this)
        seekBarAudio.setOnSeekBarChangeListener(SeekBarChangeListener());
        seekBarAudio.max=mediaPlayer.duration
        seekBarAudio.postDelayed(thread,1000)
        seekBarAudio.setProgress(mediaPlayer.getCurrentPosition())
    }



}
