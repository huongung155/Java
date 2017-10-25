package com.company;

import com.company.model.Artist;
import com.company.model.Datasource;
import com.company.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
        if(artists == null){
            System.out.println("No artists");
            return;
        }

        for(Artist artist: artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Carole King", Datasource.ORDER_BY_DESC);

        for(String album: albumsForArtist){
            System.out.println(album);
        }

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Heartless", 2);
        for(SongArtist songArtist: songArtists){
            System.out.println("Artist Name: " + songArtist.getArtistName() + ", Album Name: " + songArtist.getAlbumName() + ", Song Track: " + songArtist.getTrack());
        }

        datasource.querySongsMetadata();

        /*int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);*/

        datasource.createViewForSongArtists();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song: ");
        String title = scanner.nextLine();

        songArtists = datasource.querySongInfoView(title);
        if(songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtists) {
            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track number = " + artist.getTrack());
        }

        //Go Your Own Way" or 1=1 or "
        //SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way" or 1=1 ""

        datasource.insertSong("Touch of Grey", "Grateful Dead", "In the dark", 1);

        datasource.close();
    }
}
