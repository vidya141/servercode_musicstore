package com.myapp.db;

import com.myapp.bean.song;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Kote
 * Date: 3/16/12
 * Time: 3:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class songDB {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost/musicstore?user=root&password=admin";

    public static Connection getDBConnection(){
        Connection conn = null ;
        try{
            Class.forName( "com.mysql.jdbc.Driver" ).newInstance();
            conn = DriverManager.getConnection(CONNECTION_URL  );

        }
        catch( Exception e )
        {
            System.out.println( "Holy crap, Batman!" );
            System.out.println( e.getMessage() );
            e.printStackTrace();
        }
        return conn;
    }

    public static ArrayList<song> getSongs (Connection con)  throws SQLException{
        Statement stmt = null;
        ArrayList<song> songs = new ArrayList<song>();
        String query =
                "select * " +
                        "from songs";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                String name = rs.getString("NAME");
                String band = rs.getString("BAND");
                double price= rs.getDouble("PRICE");
                song newSong = new song(name,band,price);
                songs.add(newSong);
                System.out.println("Sending the request");
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }

        return songs;
    }

    public static void insertValues(Connection conn,String name, String band, double price){
        PreparedStatement stmt;
        try {
            String query = "INSERT INTO ";
            query += "songs (name, band, price)";
            query += " VALUES (?,?,?)";
            //System.out.println( "Query: " + query );

            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, band);
            stmt.setDouble(3, price);
            stmt.executeUpdate();
        }
        catch( SQLException e )
        {
            System.out.println( "SQLException: " + e.getMessage() );
            System.out.println( "SQLState:     " + e.getSQLState() );
            System.out.println( "VendorError:  " + e.getErrorCode() );
        }

    }



    public static void insertLoginInfo(Connection conn, String firstName, String lastName, String pwd, String email, String confirmation) {
        PreparedStatement stmt;
        try{
            String query = "INSERT INTO login_details(first_name, last_name,pwd,email,confirmation) VALUES(?,?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, pwd);
            stmt.setString(4, email);
            stmt.setString(5, confirmation);
            stmt.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println( "SQLException: " + e.getMessage() );
            System.out.println( "SQLState:     " + e.getSQLState() );
            System.out.println( "VendorError:  " + e.getErrorCode() );
        }


    }



}

