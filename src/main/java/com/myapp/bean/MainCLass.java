package com.myapp.bean;

import com.myapp.db.songDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/21/12
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainCLass {
    public static void main(String [ ] args){
        ArrayList<song> songs = new ArrayList<song>();
        Connection con = songDB.getDBConnection();
        try{
            songs = songDB.getSongsFromBand(con,"IO");
            System.out.println(songs);
        } catch (SQLException e){
            e.printStackTrace();
        }


    }
}
