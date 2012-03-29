package com.myapp.servlet;

import com.google.gson.Gson;
import com.myapp.bean.Search;
import com.myapp.bean.song;
import com.myapp.bean.songDetails;
import com.myapp.db.songDB;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/26/12
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class songDetailsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonFormat = req.getParameter("songName");
        Connection conn = songDB.getDBConnection();

        songDetails songDetails1 = new songDetails();
        Gson gson = new Gson();
        JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonFormat );
        System.out.println(jsonFormat);
        System.out.println(json);
        String songName = json.getString("songName");
        try{
             songDetails1 = songDB.getSongDetails(conn, songName);

            } 
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String jsonSongDetails = gson.toJson(songDetails1);
        resp.getOutputStream().print(jsonSongDetails);
        resp.getOutputStream().flush();
        resp.setHeader("Access-Control-Allow-Origin", "*");

    }
}
