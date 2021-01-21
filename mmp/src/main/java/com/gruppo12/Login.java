package com.gruppo12;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("Username");
        String pass = request.getParameter("Password");
        if (Validate.checkUser(user, pass)) {
            HttpSession login = request.getSession();
            login.setAttribute("user", user);
            if (Validate.checkAdmin(user, pass)) {
                login.setAttribute("admin", "true");
            }
            response.sendRedirect("home");
        } else {
            response.sendRedirect("auth");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("auth");
    }
}
