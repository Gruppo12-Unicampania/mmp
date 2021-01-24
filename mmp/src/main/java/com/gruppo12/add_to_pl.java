package com.gruppo12;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class add_to_pl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession(false);
        if (login != null) {
            String Username = (String) login.getAttribute("user");
            String id_user;
            id_user = getList.getId(Username);
            String Play_name = (String) login.getAttribute("playlist");
            String id_playlist;
            id_playlist = getList.getPlaylistId(id_user, Play_name);
            String id_song = request.getParameter("id");
            DBUpload.insertSong(id_song, id_user, id_playlist);
            response.sendRedirect("playlist_space");

        }
    }
}
