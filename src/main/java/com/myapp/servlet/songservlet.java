package com.myapp.servlet;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/16/12
 * Time: 3:14 AM
 * To change this template use File | Settings | File Templates.
 */
import com.myapp.db.songDB;
import java.io.IOException;

import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.SQLException;


import com.myapp.bean.song;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class songservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String jsonSong = getSongsInJson().toString();
        resp.getOutputStream().print(jsonSong);
        resp.getOutputStream().flush();
        resp.setHeader("Access-Control-Allow-Origin", "*");



    }

    private JSONArray getSongsInJson() {
        Connection con = songDB.getDBConnection();
        ArrayList<song> songs = new ArrayList<song>();
        try {
            songs = songDB.getSongs(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONArray array = new JSONArray();
        array.addAll(songs);
        return array;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("In the servlet");
        String jsonFormat = req.getParameter("songs");

        Gson gson = new Gson();
        song songObj = gson.fromJson(jsonFormat,song.class);
        System.out.println(jsonFormat);
        Connection conn = songDB.getDBConnection();
        songDB.insertValues(conn, songObj.getSongName(), songObj.getBandName(), songObj.getSongPrice());

    }

}

