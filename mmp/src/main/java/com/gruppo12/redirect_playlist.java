package com.gruppo12;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class redirect_playlist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pl;
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
        pl = request.getParameter("Playlist_name");
        login.setAttribute("playlist", pl);
        response.sendRedirect("playlist_space");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("main_space");
    }

}
