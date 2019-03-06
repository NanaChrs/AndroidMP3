package com.morgane.isen.essai_mp3_1.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
class AudioFile : Comparable<AudioFile> {

    override fun compareTo(other: AudioFile): Int {
        /*run {
            return if (name.equals(other.name)) {
                artist.compareTo(other.artist)
            } else name.compareTo(other.name)
        }*/
        return name.compareTo(other.name);
    }

    @ColumnInfo
    var name: String = ""

    @Embedded
    var artist: String = ""

    @ColumnInfo
    var album: String = ""

    @ColumnInfo
    var path: String = ""

    @NonNull
    @PrimaryKey
    var i = 0
}
