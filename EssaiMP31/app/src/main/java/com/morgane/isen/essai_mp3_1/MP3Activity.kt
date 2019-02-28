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

class MP3Activity : AppCompatActivity(), AudioFileListener {

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    override fun onViewAudio(audioFile: AudioFile?) {
        val fragment = AudioFragment()
        val bundle = Bundle()
        bundle.putString(Constants.Audio.EXTRA_NAME, audioFile?.name)
        bundle.putString(Constants.Audio.EXTRA_ARTIST, audioFile?.artist)
        bundle.putString(Constants.Audio.EXTRA_ALBUM, audioFile?.album)
        fragment.arguments = bundle

        Log.d("fragmentonview", fragment.arguments.toString())

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
        val fragment = AudiosFragment();
        setContentView(R.layout.mp3activity)

        // Retrieve the login passed as parameter
        val intent = intent
        if (null != intent) {
            val extras = intent.extras
        }
        getSupportFragmentManager().inTransaction {
            add(R.id.container2, fragment)
        }
    }
}
