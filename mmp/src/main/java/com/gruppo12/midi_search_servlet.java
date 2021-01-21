package com.gruppo12;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class midi_search_servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name;
        String user = null;
        HttpSession login = request.getSession(false);
        if (login != null) {
            user = (String) login.getAttribute("user");
            if (user == null) {
                response.sendRedirect("Logout");
            }
        } else {
            response.sendRedirect("Logout");
        }
        name = request.getParameter("name");
        login.setAttribute("name", name);
        response.sendRedirect("midi_search_bar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("main_space");
    }

}
