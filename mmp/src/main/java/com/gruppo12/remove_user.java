package com.gruppo12;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class remove_user extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String path = getServletContext().getRealPath("") + File.separator + "songs" + File.separator + id;
        DBUpload.deleteUser(id, path);
        response.sendRedirect("delete_user");
    }

}
