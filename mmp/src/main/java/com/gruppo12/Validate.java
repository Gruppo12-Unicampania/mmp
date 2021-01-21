package com.gruppo12;

import static com.gruppo12.DBUpload.PASS;
import static com.gruppo12.DBUpload.URL;
import static com.gruppo12.DBUpload.USER;
import static java.lang.System.out;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validate {

    public static boolean checkUser(String username, String pass) {
        boolean st = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps;
            ps = conn.prepareStatement("SELECT * FROM users WHERE Username=? AND Password=?");

            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Db Error!");
        }
        return st;
    }

    static boolean checkAdmin(String user, String pass) {
        boolean st = false;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps;
            ps = conn.prepareStatement("SELECT * FROM users WHERE Username=? AND Password=? AND Admin='1'");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            out.println("Db Error!");
        }
        return st;
    }
}
