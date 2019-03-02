package com.morgane.isen.essai_mp3_1.pojo

class AudioFile : Comparable<AudioFile> {

    override fun compareTo(other: AudioFile): Int {
        /*run {
            return if (name.equals(other.name)) {
                artist.compareTo(other.artist)
            } else name.compareTo(other.name)
        }*/
        return name.compareTo(other.name);
    }

    var name: String = ""
    var artist: String = ""
    var album: String = ""
    var path: String = ""
    var i = 0
}
