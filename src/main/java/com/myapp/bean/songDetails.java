package com.myapp.bean;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/26/12
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class songDetails {
    private String songName;
    private String artist;
    private String genre;
    private int rating;
    private int year;
    
    public void setSongName(String songName){
        this.songName = songName;
    }
    
    public void setArtist(String artist){
        this.artist = artist;
    }
    
    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public String getSongName(){
        return  songName;
    }
    
    public String getArtist(){
        return artist;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public int getRating(){
        return rating;
    }
    
    public  int getYear(){
        return year;
    }
}
