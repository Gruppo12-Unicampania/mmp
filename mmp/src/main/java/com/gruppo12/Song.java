package com.gruppo12;

import java.io.Serializable;
import java.io.*;

public class Song implements Serializable {

    public String link_url;
    public String cover;
    public String name;
    public String album;
    public String composer;
    public String singer;  

    public Song(String link_url, String cover, String name, String album, String composer, String singer) {
        this.link_url = link_url;
        this.cover = cover;
        this.name = name;
        this.album = album;
        this.composer = composer;
        this.singer = singer;
    }
}
