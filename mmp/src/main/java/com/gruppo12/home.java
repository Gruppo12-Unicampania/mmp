package com.gruppo12;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rs = request.getRequestDispatcher("home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession login;
        login = request.getSession(false);
        if (login != null) {
            String superadmin;
            superadmin = "amministratore";
            String user;
            user = (String) login.getAttribute("user");
            String admin;
            admin = (String) login.getAttribute("admin");
            if (user != null) {
                if ("0".equals(admin)) {
                    response.sendRedirect("main_space");
                } else {
                    boolean condition = user.equals(superadmin);
                    if (condition) {
                        login.setAttribute("superadmin", "true");
                        response.sendRedirect("superadmin_panel");
                    } else {
                        response.sendRedirect("setting");
                    }
                }
            } else {
                response.sendRedirect("auth");
            }
        } else {
            response.sendRedirect("auth");
        }
    }

}
