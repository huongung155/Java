package com.company;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {
        Album album = new Album("Stormbringer");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        album.addSong("Soldier of fortune", 3.13);
        albums.add(album);

        album = new Album("For those about to rock");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<Song>();
        albums.get(0).addToPlayList("You can't do it right", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(0).addToPlayList("Speed king", playList);  // Does not exist
        albums.get(0).addToPlayList(9, playList);
        albums.get(1).addToPlayList(8, playList);
        albums.get(1).addToPlayList(3, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(24, playList);  // There is no track 24

        play(playList);
    }

    public static void play(LinkedList<Song> playList){
        Scanner scan = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> playListIterator = playList.listIterator();
        if(playList.size() == 0){
            System.out.println("Play list is empty");
            return;
        }else {
            System.out.println("Now playing " + playListIterator.next().toString());
        }

        while (!quit){
            int action = scan.nextInt();
            scan.nextLine();

            switch (action){
                case 0:
                    System.out.println("Playing done");
                    quit = true;
                    break;
                case 1:
                    if(!forward){
                        if(playListIterator.hasNext())
                            playListIterator.next();
                        forward = true;
                    }
                    if(playListIterator.hasNext()){
                        System.out.println(playListIterator.next().toString());
                    }else {
                        System.out.println("It is the end of the list");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward){
                        if(playListIterator.hasPrevious())
                            playListIterator.previous();
                        forward = false;
                    }
                    if(playListIterator.hasPrevious()){
                        playListIterator.previous();
                    }else {
                        System.out.println("It is the head of the list");
                        forward = true;
                    }
                    break;
                case 3:
                    if(forward){
                        if(playListIterator.hasPrevious()){
                            System.out.println("Now playing " + playListIterator.previous().toString());
                            forward = false;
                        }else {
                            System.out.println("It is the head of the list");
                        }
                    }else {
                        if(playListIterator.hasNext()){
                            System.out.println("Now playing " + playListIterator.next().toString());
                            forward = true;
                        }else {
                            System.out.println("It is the end of the list");
                        }
                    }
                    break;
                case 4:
                    printMenu();
                    break;
                case 5:
                    printList(playList);
                    break;
                case 6:
                    if(playList.size() > 0){
                        playListIterator.remove();
                        if(playListIterator.hasNext()){
                            System.out.println("Now playing " + playListIterator.next().toString());
                        }else if(playListIterator.hasPrevious()){
                            System.out.println("Now playing " + playListIterator.previous().toString());
                        }
                    }
                    break;
            }
        }
    }

    public static void printList(LinkedList<Song>linkedList){
        System.out.println("============================");
        Iterator<Song> i = linkedList.iterator();
        while (i.hasNext())
            System.out.println(i.next().toString());
        System.out.println("============================");
    }

    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions.\n" +
                "6 - delete current song from playlist");

    }
}
