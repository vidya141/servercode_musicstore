package com.myapp.servlet;

import com.google.gson.Gson;
import com.myapp.bean.Customer;
import com.myapp.db.songDB;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/28/12
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class validateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String jsonFormat = req.getParameter("user");
        Connection conn = songDB.getDBConnection();
        Gson gson = new Gson();
        Customer customerObj = gson.fromJson(jsonFormat,Customer.class);
        String valid="";
        try{
             valid = songDB.getValidation(conn,customerObj.getEmail(),customerObj.getPwd());
        } catch(SQLException e){
            e.printStackTrace();
        }

        JSONObject json = new JSONObject();
       // json.put("valid",)
        resp.setHeader("Access-Control-Allow-Origin", "*");

    }
}
