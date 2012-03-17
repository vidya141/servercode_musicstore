package com.myapp.servlet;

import com.google.gson.Gson;
import com.myapp.bean.Customer;
import com.myapp.db.songDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/16/12
 * Time: 3:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class signupservlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String jsonFormat = req.getParameter("signupDetails");
        Connection conn = songDB.getDBConnection();
        Gson gson = new Gson();
        Customer customerObj = gson.fromJson(jsonFormat,Customer.class);
        customerObj.setConfirmation("N");
        System.out.println(jsonFormat);
        songDB.insertLoginInfo(conn, customerObj.getFirstName(), customerObj.getLastName(), customerObj.getPwd(), customerObj.getEmail(), customerObj.getConfirmation());
        //sendAConfirmationEmail(customerObj.getEmail());

    }


}
