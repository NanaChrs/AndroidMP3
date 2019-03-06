package com.morgane.isen.essai_mp3_1.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.morgane.isen.essai_mp3_1.R;
import com.morgane.isen.essai_mp3_1.adapters.AudioFilesAdapter;
import com.morgane.isen.essai_mp3_1.asynch.RetrieveAudioFiles;
import com.morgane.isen.essai_mp3_1.asynch.RetrieveAudioFilesFromDatabase;
import com.morgane.isen.essai_mp3_1.interfaces.AudioChangeListener;
import com.morgane.isen.essai_mp3_1.interfaces.AudioFileListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AudiosFragment extends Fragment implements AudioChangeListener,AdapterView.OnItemClickListener {

    private RetrieveAudioFilesFromDatabase mAudioAsyncTask;
    private ListView mListView;
    private AudioFileListener mListener;
    public AudiosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_audios, container, false);
        mListView= (ListView) RootView.findViewById(R.id.audiosListView);
        // Set adapter with no elements to let the ListView display the empty view
        mListView.setAdapter(
                new ArrayAdapter<AudioFile>(getActivity(),
                        android.R.layout.simple_list_item_1, new ArrayList<AudioFile>()));
        mListView.setOnItemClickListener(this);
        return RootView;
    }

    @Override
    public void onStart() {

        super.onStart();
        mAudioAsyncTask = new RetrieveAudioFilesFromDatabase(this);
        mAudioAsyncTask.execute("");
    }

    @Override
    public void onTweetRetrieved(List<AudioFile> audioFileList) {
        final AudioFilesAdapter adapter = new AudioFilesAdapter(audioFileList);
        mListView.setAdapter(adapter);
        adapter.setListener(mListener);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if (activity instanceof AudioFileListener){
            mListener = (AudioFileListener) activity;
        }
    }

    public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
        if (null!= mListener) {
            final AudioFile audioFile= (AudioFile) adapter.getItemAtPosition(position);
            Log.d("bijour", audioFile.getAlbum());
            mListener.onViewAudio(audioFile);
        }
    }
}
