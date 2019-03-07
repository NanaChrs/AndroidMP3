package com.morgane.isen.essai_mp3_1.asynch;

import com.morgane.isen.essai_mp3_1.GlobalMediaPlayer;
import com.morgane.isen.essai_mp3_1.pojo.AudioFile;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class JsonUpdate implements GlobalMediaPlayer {

    public void checkJson(){
        for (AudioFile file : audioFiles){
            //ouvrir Json
            //s'il manque infos dans tags
            //searchInfo(file); renvoi une liste de string avec l'album, l'artiste, la date et la duree


            //si file n'est pas enregistré
            //editJson(file);

        }
    }
    public String[] searchInfo(String nom, String date, String artiste, String album){
        String[] infos={nom, album, artiste, date, "duree"};
        String artist = artiste;
        if (nom!=""){
            try {
                Document doc = Jsoup.connect("https://www.allmusic.com/search/all/"+nom).get();
                Element content = doc.getElementById("search-result");
                Elements links = content.getElementsByClass("song");
                for (Element link : links) {
                    Elements arti = link.getElementsByClass("performers");
                    for(Element art : arti){
                        if(artist==""){
                            artist=art.text();
                        }
                        if (art.getElementsByAttributeValue("href", artist).hasText()){
                            String linkHref = link.attr("href");
                            String linkText = link.text();
                            Document dc = Jsoup.connect(linkText).get();
                            Elements cont = dc.getElementsContainingText("inAlbum");//zone avec toutes les informations disponibles
                            if(album==""){
                                infos[1]=cont.get(3).child(0).child(0).child(0).text();
                            }
                            if(artiste==""){
                                infos[2]=cont.get(3).child(1).child(0).text();
                            }
                            if(date==""){
                                infos[3]=cont.get(2).text();
                            }
                            infos[4]=cont.get(5).child(0).text();
                        }
                    }
                }
            }
            catch (IOException e){
            }
        }
        return infos;
    }
    public void editJson(String nom, String date, String artiste, String album){

    }
}