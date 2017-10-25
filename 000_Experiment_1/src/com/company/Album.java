package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by M4800 on 18-Jan-17.
 */
public class Album {
    private String name;
    private ArrayList<Song> songs;

    public Album(String name) {
        this.name = name;
        this.songs = new ArrayList<Song>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Song findSong(String title){
        for(Song i : songs){
            if(i.getTitle().equals(title)){
                return i;
            }
        }
        return null;
    }

    public boolean addSong(String title, double duration){
        if(findSong(title) == null){
            songs.add(new Song(title, duration));
            return true;
        }
        return false;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList){
        if(trackNumber - 1 >= 0 && trackNumber - 1 < songs.size()){
            playList.add(this.songs.get(trackNumber - 1));
            return true;
        }
        System.out.println("The album does not have this track number");
        return false;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList){
        Song foundSong = findSong(title);
        if(foundSong != null){
            playList.add(foundSong);
            return true;
        }
        System.out.println("This song already exists in the play list");
        return false;
    }
}
