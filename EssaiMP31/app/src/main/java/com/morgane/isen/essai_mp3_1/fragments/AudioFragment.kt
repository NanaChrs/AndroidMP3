package com.morgane.isen.essai_mp3_1.fragments

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.morgane.isen.essai_mp3_1.*

import com.morgane.isen.essai_mp3_1.listener.SeekBarChangeListener
import com.morgane.isen.essai_mp3_1.pojo.AudioFile
import kotlinx.android.synthetic.main.fragment_audio.*
import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer.*
import com.morgane.isen.essai_mp3_1.thread.ThreadSeekBar
import java.lang.Math.random
import kotlin.random.Random
import android.content.Intent
import android.widget.ImageButton


class AudioFragment : Fragment(), GlobalMediaPlayer, MediaPlayer.OnPreparedListener{

    override fun onPrepared(mp: MediaPlayer?) {
        seekBarAudio.max=mediaPlayer.duration
        seekBarAudio.postDelayed(thread,1000)
    }

    private var thread : ThreadSeekBar? = null
    private var aleatoire = "Non Aleatoire"
    private var isPaused = false
    private var buttonPlay = "Play"
    var seekBarAudio = SeekBar(MP3Application.getContext())

    fun getAleatoire() : String{
        return this.aleatoire
    }

    fun getPlay() : String{
        return this.buttonPlay
    }

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
        aleatoire = arguments!!.getString(Constants.Audio.EXTRA_ALEATOIRE)
        if(aleatoire=="Aleatoire") {
            (view.findViewById<View>(R.id.aleatoireButton) as ImageButton).setImageResource(R.drawable.ic_shuffle_red_24dp)
        } else {
            (view.findViewById<View>(R.id.aleatoireButton) as ImageButton).setImageResource(R.drawable.ic_shuffle_black_24dp)

        }
        buttonPlay= arguments!!.getString(Constants.Audio.EXTRA_PLAY)
        if(buttonPlay=="Play") {
            (view.findViewById<View>(R.id.playbis) as ImageButton).setImageResource(R.drawable.ic_play_arrow_black_24dp)
        } else {
            (view.findViewById<View>(R.id.playbis) as ImageButton).setImageResource(R.drawable.ic_pause_black_24dp)

        }
        (view.findViewById<View>(R.id.nextButton) as ImageButton).setImageResource(R.drawable.ic_next_black_24dp)
        seekBarAudio= view.findViewById(R.id.seekBar) as SeekBar
        progressMusic()
        mediaPlayer.reset()
        val PATH_TO_FILE = arguments!!.getString(Constants.Audio.EXTRA_PATH)
        mediaPlayer.setDataSource(PATH_TO_FILE)
        mediaPlayer.prepare()
        if (buttonPlay=="Pause") {
            mediaPlayer.start()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var newSong = true

        if( thread== null){
            thread = ThreadSeekBar(seekBarAudio,isPaused)
        }

        //playbis.setText(buttonPlay)
        //aleatoireButton.setText(aleatoire)
        changeButton()
        val PATH_TO_FILE = arguments!!.getString(Constants.Audio.EXTRA_PATH)


        playbis.setOnTouchListener {  _, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_UP -> {
                    if (buttonPlay=="Play") {
                        buttonPlay="Pause"
                        changeButton()
                        if (!isPaused || newSong) {
                            mediaPlayer.stop()
                            mediaPlayer.reset()
                            mediaPlayer.setDataSource(PATH_TO_FILE)
                            mediaPlayer.prepare()
                            isPaused = false
                        }
                        if (thread == null) {
                            thread = ThreadSeekBar(seekBarAudio, isPaused)
                        }
                        newSong = false
                        mediaPlayer.start()
                    } else {
                        buttonPlay="Play"
                        changeButton()
                        println("bouton appuyé")
                        mediaPlayer.pause()
                        isPaused = true
                        newSong = false
                        //mediaPlayer?.seekTo(0)
                        thread=null
                    }
                    passPlayAndAlea()
                }
            }
            true
        }

        aleatoireButton.setOnTouchListener { _, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_UP -> {
                    Log.e("Alea avant",aleatoire)
                    if (aleatoire=="Non Aleatoire"){
                        aleatoire="Aleatoire"
                    } else {
                        aleatoire="Non Aleatoire"
                    }
                    Log.e("Alea apres",aleatoire)
                    changeButton()
                    passPlayAndAlea()
                }
            }
            true
        }

        nextButton.setOnTouchListener { _, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_UP -> {
                    var index=0
                    buttonPlay="Pause"
                    passPlayAndAlea()
                    println("bouton appuyé")
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    val audio = getTrackById(PATH_TO_FILE)
                    Log.d("audiotest",audio.toString())
                    Log.e("alea",aleatoire)
                    if (aleatoire!="Non Aleatoire"){
                        index = Random.nextInt(0, audioFiles.size)
                    } else {
                        index=audio!!.i+1
                    }
                    if (audio!=null){
                        //Log.d("audiio", audio.i.toString())
                        mediaPlayer.setDataSource(audioFiles[index].path)
                        mediaPlayer.prepare()
                        //Log.d("audioFilepat",audioFiles[audio.i+1].path )
                        mediaPlayer.start()
                        refresh(audioFiles[index], aleatoire,buttonPlay)
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

    fun refresh(audioFile: AudioFile?,aleatoire : String, play : String) {
        val fragment = AudioFragment()
        val bundle = Bundle()
        bundle.putString(Constants.Audio.EXTRA_NAME, audioFile?.name)
        bundle.putString(Constants.Audio.EXTRA_ARTIST, audioFile?.artist)
        bundle.putString(Constants.Audio.EXTRA_ALBUM, audioFile?.album)
        bundle.putString(Constants.Audio.EXTRA_PATH, audioFile?.path)
        bundle.putString(Constants.Audio.EXTRA_ALEATOIRE,aleatoire)
        bundle.putString(Constants.Audio.EXTRA_PLAY, play)
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

    fun passPlayAndAlea(){
        val datas = Bundle()
        datas.putString(Constants.Audio.EXTRA_PLAY, buttonPlay)
        datas.putString(Constants.Audio.EXTRA_ALEATOIRE, aleatoire)

        val intent = activity!!.intent
        intent.putExtras(datas)

    }

    fun changeButton(){
        if (buttonPlay=="Play") {
            playbis.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        } else {
            playbis.setImageResource(R.drawable.ic_pause_black_24dp)
        }
        if (aleatoire=="Non Aleatoire") {
            aleatoireButton.setImageResource(R.drawable.ic_shuffle_black_24dp)
        } else {
            aleatoireButton.setImageResource(R.drawable.ic_shuffle_red_24dp)
        }
    }
}
