package com.gruppo12;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class remove_song extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_song = request.getParameter("id_song");
        String path = getList.getPathFromId(id_song);
        String absoluteDiskPath = getServletContext().getRealPath(path);
        File file = new File(absoluteDiskPath);
        if (file.delete()) {
            DBUpload.RmSong(id_song);
            response.sendRedirect("delete_song");
        } else {
            response.sendRedirect(getList.getPathFromId(id_song));
        }
    }

}
