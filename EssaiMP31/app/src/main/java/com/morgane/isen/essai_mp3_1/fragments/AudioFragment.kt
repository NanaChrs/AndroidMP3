package com.morgane.isen.essai_mp3_1.fragments

import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.morgane.isen.essai_mp3_1.Constants
import com.morgane.isen.essai_mp3_1.R

class AudioFragment : Fragment() {

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
