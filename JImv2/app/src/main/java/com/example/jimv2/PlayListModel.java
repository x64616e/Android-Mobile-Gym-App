package com.example.jimv2;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayListModel  implements Serializable{
    ArrayList<Song> songs;

    public PlayListModel(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
