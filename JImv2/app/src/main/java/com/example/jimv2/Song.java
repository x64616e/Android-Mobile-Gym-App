package com.example.jimv2;

import java.io.Serializable;

public class Song implements Serializable {
    private String Name;
    private String Artist;
    private int song;
    private int picture;

    public Song(String name, String artist, int song , int picture) {
        Name = name;
        Artist = artist;
        this.song = song;
        this.picture = picture;
    }

    public Song() {
    }


    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return Name;
    }

    public String getArtist() {
        return Artist;
    }

    public int getSong() {
        return song;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public void setSong(int song) {
        this.song = song;
    }
}
