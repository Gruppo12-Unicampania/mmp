package com.gruppo12;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.gruppo12.DBUpload.PASS;
import static com.gruppo12.DBUpload.URL;
import static com.gruppo12.DBUpload.USER;

public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name;
        name = request.getParameter("username");
        String mymail;
        mymail = request.getParameter("email");
        String pass;
        pass = request.getParameter("password");
        String pass_confirm;
        pass_confirm = request.getParameter("confirm_password");
        boolean pass_condition = pass.equals(pass_confirm);
        if ((name.length() >= 6) && (name.length() <= 16) && (pass.length() < 16) && (pass.length() > 6) && (pass_condition) && (mymail.length() <= 50) && (mymail.length() >= 6)) {
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                }
                Connection conn = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement ps;
                ps = conn.prepareStatement("INSERT INTO users(Username,Password,Email) VALUES(?,?,?)");
                ps.setString(1, name);
                ps.setString(2, pass);
                ps.setString(3, mymail);
                int i;
                i = ps.executeUpdate();
                if (i > 0) {
                    {
                        response.sendRedirect("auth");
                    }
                } else {
                    response.sendRedirect("register");
                }

            } catch (SQLException se) {
                response.sendRedirect("register");
            }
        } else {
        }

    }
}
