package com.gruppo12;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class rmsong extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession(false);
        String pl = (String) login.getAttribute("playlist");
        String id_song = request.getParameter("id_song");
        String user = (String) login.getAttribute("user");
        String id_user = getList.getId(user);
        String id_playlist = getList.getPlaylistId(id_user, pl);
        DBUpload.RmSongInPlaylist(id_song, id_playlist);
        response.sendRedirect("pl_remove_song");
    }

}
