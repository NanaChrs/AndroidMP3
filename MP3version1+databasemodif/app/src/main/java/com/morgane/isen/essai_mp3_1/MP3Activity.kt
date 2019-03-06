package com.morgane.isen.essai_mp3_1

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.morgane.isen.essai_mp3_1.fragments.AudioFragment
import com.morgane.isen.essai_mp3_1.fragments.AudiosFragment

import com.morgane.isen.essai_mp3_1.interfaces.AudioFileListener
import com.morgane.isen.essai_mp3_1.pojo.AudioFile
import kotlinx.android.synthetic.main.fragment_audio.*
import android.widget.Toast



class MP3Activity : AppCompatActivity(), AudioFileListener{

    override fun onViewAudio(audioFile: AudioFile?) {
        val fragment = AudioFragment()
        val bundle = Bundle()
        val datas = getPlayAndAlea()
        Log.e("datas",datas.toString())
        bundle.putString(Constants.Audio.EXTRA_NAME, audioFile?.name)
        bundle.putString(Constants.Audio.EXTRA_ARTIST, audioFile?.artist)
        bundle.putString(Constants.Audio.EXTRA_ALBUM, audioFile?.album)
        bundle.putString(Constants.Audio.EXTRA_PATH, audioFile?.path)
        if (datas.get(0)==null) {
            bundle.putString(Constants.Audio.EXTRA_ALEATOIRE, "Non Aleatoire")
            bundle.putString(Constants.Audio.EXTRA_PLAY, "Play")
        } else {
            bundle.putString(Constants.Audio.EXTRA_ALEATOIRE, datas.get(1))
            bundle.putString(Constants.Audio.EXTRA_PLAY, datas.get(0))
        }
        fragment.arguments = bundle

        Log.d("onViewAudio", fragment.arguments.toString())

        findViewById<View>(R.id.audiosListView).visibility = View.INVISIBLE

        val tx = supportFragmentManager.beginTransaction()
        //tx.add(R.id.container2, fragment)
        tx.replace(R.id.container2, fragment)
        tx.addToBackStack(null)
        tx.commit()

        //getSupportFragmentManager().inTransaction {
        //    add(R.id.container2, fragment)
        //}

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = AudiosFragment()
        setContentView(R.layout.mp3activity)

        // Retrieve the login passed as parameter
        val intent = intent
        if (null != intent) {
            val extras = intent.extras
        }

        val tx = supportFragmentManager.beginTransaction()
        //tx.add(R.id.container2, fragment)
        tx.add(R.id.container2, fragment,"TAG")
        tx.show(fragment)
        tx.addToBackStack("TAG")
        tx.commit()


    }

    override fun onBackPressed() {
        val fragment = AudiosFragment()
        super.onBackPressed()
        val tx = supportFragmentManager.beginTransaction()
        //tx.add(R.id.container2, fragment)
        tx.replace(R.id.container2, fragment,"TAG")
        tx.addToBackStack("TAG")
        tx.commit()

    }

    fun getPlayAndAlea(): ArrayList<String>{
        val playAndAlea = ArrayList<String>()
        val datas = intent.extras
        if (datas != null) {
            val play = datas.getString(Constants.Audio.EXTRA_PLAY)
            val alea = datas.getString(Constants.Audio.EXTRA_ALEATOIRE)
            playAndAlea.add(play)
            playAndAlea.add(alea)
        }
        return playAndAlea
    }

}
