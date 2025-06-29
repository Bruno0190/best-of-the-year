package com.bestofyear.best_of_the_year;

public class Song {

    private int id;
    private String title;

    public Song(int id, String title){

        this.id = id;
        this.title = title;

    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    
}
