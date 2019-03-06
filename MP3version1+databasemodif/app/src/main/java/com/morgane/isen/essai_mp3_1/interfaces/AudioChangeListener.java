package com.morgane.isen.essai_mp3_1.interfaces;

import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.List;

public interface AudioChangeListener {
    public void onTweetRetrieved(List<AudioFile> audioFileList);
}
