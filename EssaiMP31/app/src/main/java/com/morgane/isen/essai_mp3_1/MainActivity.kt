package com.morgane.isen.essai_mp3_1

import android.Manifest
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.morgane.isen.essai_mp3_1.fragments.AudioFragment
import com.morgane.isen.essai_mp3_1.fragments.AudiosFragment
import com.morgane.isen.essai_mp3_1.interfaces.AudioFileListener

import com.morgane.isen.essai_mp3_1.pojo.AudioFile
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_audios.*

import java.util.ArrayList

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        println("hello")
        val fragment = AudioFragment()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener{
            val homeIntent = getHomeActivityIntent()
            startActivity(homeIntent)
        }

        /*fragment.arguments = savedInstanceState
        Log.d("fragmentonCreate",fragment.arguments.toString())

        supportFragmentManager.inTransaction {
            add(R.id.container, fragment)
        }
        */


    }

    private fun getHomeActivityIntent(): Intent {
        val intent = Intent(this, MP3Activity::class.java)
        val extras = Bundle()
        intent.putExtras(extras)
        return intent
    }

}
