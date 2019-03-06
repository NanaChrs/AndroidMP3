package com.morgane.isen.essai_mp3_1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.morgane.isen.essai_mp3_1.MP3Application;
import com.morgane.isen.essai_mp3_1.R;
import com.morgane.isen.essai_mp3_1.interfaces.AudioFileListener;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import java.util.List;

public class AudioFilesAdapter extends BaseAdapter implements View.OnClickListener {

    private final List<AudioFile> mAudioFiles;
    private final LayoutInflater mInflater;
    private AudioFileListener mListener;

    public AudioFilesAdapter(List<AudioFile> mAudioFiles){
        this.mAudioFiles=mAudioFiles;
        mInflater = LayoutInflater.from(MP3Application.getContext());
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if (null != mListener){
            final AudioFile audioFile = (AudioFile) getItem(position);
            mListener.onViewAudio(audioFile);
        }
    }

    public void setListener(AudioFileListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return null != mAudioFiles ? mAudioFiles.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mAudioFiles ? mAudioFiles.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView){
            convertView = mInflater.inflate(R.layout.list_audiofile, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AudioFile audioFile= (AudioFile) getItem(position);
        holder.name.setText(audioFile.getName());
        holder.artist.setText(audioFile.getArtist());
        holder.album.setText(audioFile.getAlbum());

        return convertView;
    }

    private class ViewHolder {
        public TextView name;
        public TextView artist;
        public TextView album;

        public ViewHolder(View view){
            name = (TextView) view.findViewById(R.id.title);
            artist = (TextView) view.findViewById(R.id.artist);
            album = (TextView) view.findViewById(R.id.album);
        }
    }
}
