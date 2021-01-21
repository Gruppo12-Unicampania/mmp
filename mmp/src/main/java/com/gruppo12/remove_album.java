package com.gruppo12;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class remove_album extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession(false);
        String user = (String) login.getAttribute("user");
        String id = getList.getId(user);
        String album_name = request.getParameter("Album_name");
        String path = getServletContext().getRealPath("") + File.separator + "songs" + File.separator + id + File.separator + album_name;
        DBUpload.deleteAlbum(id, path, album_name);
        response.sendRedirect("delete_album");
    }

}
