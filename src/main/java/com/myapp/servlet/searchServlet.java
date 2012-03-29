package com.myapp.servlet;

import com.google.gson.Gson;
import com.myapp.bean.Search;
import com.myapp.bean.song;
import com.myapp.db.songDB;
import net.sf.json.JSONArray;

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
 * Date: 3/20/12
 * Time: 2:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class searchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonFormat = req.getParameter("search");
        Connection conn = songDB.getDBConnection();
        Gson gson = new Gson();
        Search search= gson.fromJson(jsonFormat,Search.class );
        System.out.println(search.getSearchValue());
        System.out.println(search.getDropDownValue());
        ArrayList<song> songs = new ArrayList<song>();
        try{
            if(search.getDropDownValue().equals("Band")){
                songs = songDB.getSongsFromBand(conn, search.getSearchValue());
            }else {
                songs = songDB.getSongsFromSong(conn, search.getSearchValue());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONArray array = new JSONArray();
        array.addAll(songs);
        String jsonSearchResults =  array.toString();
        resp.getOutputStream().print(jsonSearchResults);
        resp.getOutputStream().flush();
        resp.setHeader("Access-Control-Allow-Origin", "*");
    }
}
