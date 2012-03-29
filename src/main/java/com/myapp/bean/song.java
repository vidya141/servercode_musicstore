package com.myapp.bean;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/16/12
 * Time: 3:12 AM
 * To change this template use File | Settings | File Templates.
 */

public class song {
    public song(){
    }

    public song(String title, String band, double price){
        songName = title;
        bandName = band;
        songPrice = price;
    }


    public String getSongName(){
        return songName;
    }

    public String getBandName(){
        return bandName;
    }

    public void setSongName(String name){
        songName = name;

    }

    public void setBandName(String band){
        bandName = band;
    }

    public void setSongPrice(double songPrice){
        songPrice = songPrice;
    }

    public double getSongPrice(){
        return songPrice;
    }

    /* String representation of the class*/
    public String toString(){
        return ("\"" + songName + "\"by " + bandName + "cost $" + songPrice);


    }


    /*Instance variables*/
    private String songName;
    private double songPrice;
    private String bandName;




}


