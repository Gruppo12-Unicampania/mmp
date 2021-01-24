<%@page import="com.gruppo12.getList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession login = request.getSession(false);
    if (login != null) {
        String user = (String) login.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Logout");
        } else {
            int id = Integer.parseInt(getList.getId(user));
            if (id != 1) {
                response.sendRedirect("main_space");
            }
        }
    } else {
        response.sendRedirect("Logout");
    }
    login.removeAttribute("pl");
    login.removeAttribute("player");

    login.removeAttribute("album");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta content="IE=edge" http-equiv="X-UA-Compatible">
        <meta content="Mobirise v5.2.0, mobirise.com" name="generator">
        <meta content="width=device-width, initial-scale=1, minimum-scale=1" name="viewport">
        <link href="assets/images/mbr-121x121.png" rel="shortcut icon" type="image/x-icon">
        <meta content="" name="description">
        <title>Admin Panel</title>
        <link href="assets/web/assets/mobirise-icons2/mobirise2.css" rel="stylesheet">
        <link href="assets/tether/tether.min.css" rel="stylesheet">
        <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/bootstrap/css/bootstrap-grid.min.css" rel="stylesheet">
        <link href="assets/bootstrap/css/bootstrap-reboot.min.css" rel="stylesheet">
        <link href="assets/dropdown/css/style.css" rel="stylesheet">
        <link href="assets/socicon/css/styles.css" rel="stylesheet">
        <link href="assets/theme/css/style.css" rel="stylesheet">
        <link href="assets/mobirise/css/mbr-additional.css" rel="preload">
        <link href="assets/mobirise/css/mbr-additional.css" rel="stylesheet" type="text/css">
        <style> .btn {
                height: 40px;
                width: 300px;
                border-radius: 15px 50px 30px;
                border: 3px solid black;
            }</style>
    </head>
    <body>
        <section class="menu cid-s48OLK6784" id="menu1-h">
            <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
                <div class="container">
                    <div class="navbar-brand">
                        <span class="navbar-logo"><a href="home"><img alt="Myplayer" src="assets/images/mbr-121x121.png" style="height: 3.8rem;"></a></span> <span class="navbar-caption-wrap"><a class="navbar-caption text-black display-7" href="home">My Music Player</a></span>
                    </div><button aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler" data-target="#navbarSupportedContent" data-toggle="collapse" type="button">
                        <div class="hamburger">
                            <span></span> <span></span> <span></span> <span></span>
                        </div></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav nav-dropdown nav-right" data-app-modern-menu="true">
                            <li class="nav-item"></li>
                            <li class="nav-item">
                                <a class="nav-link link text-black display-4" href="Logout">Logout</a>
                            </li>
                        </ul>
                    </div>
                    </section>
                    <p> </p> 
                    <p> </p>
                    <p> </p> 
                    <p> </p>
                    <div align="center"> <h1>Admin Panel</h1> </div>
                    <p> </p> 
                    <p> </p>
                    <div align="center"> <h1>Actions:</h1> </div>
                    <div align="center">
                        <p></p>
                        <p><a class="btn" href="delete_user"> Delete Users</a></p>
                    </div>
                    <div align="center">
                        <p></p>
                        <p><a class="btn" href="make_vip">Make Vip</a></p>
                    </div>
                    <div align="center">
                        <p></p>
                        <p><a class="btn" href="make_admin">Make Admin</a></p>
                    </div>
                    </body>
                    </html>
