<%@page import="java.io.File"%>
<%@page import="com.gruppo12.getList"%>
<%
    HttpSession login = request.getSession(false);
    String player = null;
    String pl = null;
    String id_pl = null;
    String id_user = null;
    if (login != null) {
        String user = (String) login.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Logout");
        }
        pl = (String) login.getAttribute("playlist");
        id_user = getList.getId(user);
        id_pl = getList.getPlaylistId(id_user, pl);
        player = (String) login.getAttribute("player");
        if (player == null) {
            response.sendRedirect("playlist_space");
        }
    } else {
        response.sendRedirect("Logout");
    }
    login.removeAttribute("album");
%>

<html>
    <head lang="en">
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
        <meta charset="UTF-8">
        <meta content="width=device-width,user-scalable=no" name="viewport">
        <title>Player</title>
        <link href="res/style.css" rel="stylesheet">
        <script src="js/lib/zepto.min.js">
        </script>
        <script src="js/lib/underscore-min.js">
        </script>
    </head>
    <% String bgPath = getList.getPlaylistImage(id_user, pl);
        bgPath = bgPath.replace(File.separator, "/");
    %>
    <body>
        <style>
            body{
                font-family: "Arial", "sans-serif";
                font-size: 16px;
                background: url("<%=bgPath%>") no-repeat;
                backdrop-filter: opacity(.7);
                backdrop-filter: blur(32px);
                background-size: cover;
                width: 100%;
                min-width: 320px;
                overflow: hidden;
            } </style>
        <section class="menu cid-s48OLK6784" id="menu1-h">
            <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
                <div class="container">
                    <div class="navbar-brand">
                        <span class="navbar-logo"><a href="main_space"><img alt="Myplayer" src="assets/images/mbr-121x121.png" style="height: 3.8rem;"></a></span> <span class="navbar-caption-wrap"><a class="navbar-caption text-black display-7" href="main_space">My Music Player</a></span>
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
                </div>
            </nav>
        </section>
        <div>
            <p><a></a></p>
            <p><a></a></p>
            <p><a></a></p>
        </div>
        <div class="background">
            <header>
                <div class="playing_info">
                    <marquee behavior="alternate" direction="left" height="20px" scrollamount="1" width="100%"><span class="songName">Hello from</span> <span class="singer">gruppo12 :)</span></marquee>
                </div>
            </header>
            <div class="content">
                <div class="panelGroup">
                    <div class="panel playlist">
                        <ul class="music_list" id="music_list">
                            <li style="text-align: center;display: block;">Loading...</li>
                        </ul>
                    </div>
                    <div class="panel lyric">
                        <div class="lyric_wrap">
                            <ul id="lyric">
                                <li>My music player</li>
                            </ul>
                        </div>
                    </div>
                    <div class="panel album">
                        <div class="album_art"><img class="stateIcon"></div>
                        <div class="music_info"></div>
                    </div>
                </div>
            </div>
            <footer>
                <audio id="player" preload="auto"></audio>
                <div id="playerProgress">
                    <div class="time currentTime">
                        00:00
                    </div>
                    <div class="progressbar" id="progressbar">
                        <span class="bar"></span>
                    </div>
                    <div class="time totalTime">
                        00:00
                    </div>
                </div>
                <div id="playerCtrl">
                    <div>
                        <a class="button prev"></a>
                    </div>
                    <div>
                        <a class="button play"></a>
                    </div>
                    <div>
                        <a class="button next"></a>
                    </div>
                </div>
            </footer>
        </div>
        <script> var id_playlist = <%=id_pl%>
            var id_song = <%=player%>
            var mypath = id_playlist + "/" + id_song
        </script> 
        <script id="music_list_item" type="text/html">
        <li>
            <div class="left">
                <div class="songName">{{name}}</div>
                <div class="singer">{{singer}}</div>
            </div>
            <div class="right">
                <span class="stateIcon"></span>
            </div>
        </li>
    </script> 
    <script id="music_info" type="text/html">
        <ul>
            <li class="title">{{name}}</li>
            <li class="type">{{type}}</li>
            <li>Composer: {{composer}}</li>
            <li>Testo(WIP): {{lyrics}}</li>
            <li>Singer: {{singer}}</li>
            <li>Album: {{album}}</li>
            <li>Name: {{name}}</li>

        </ul>
    </script> 
    <script src="js/progessbar.js">
    </script> 
    <script src="js/player.js">
    </script> 
    <script src="js/slidePage.js">
    </script> 
    <script src="js/main.js">
    </script> <a href="https://mobirise.site/g" style="flex: 1 1; height: 3rem; padding-left: 1rem;"></a>
    <p style="flex: 0 0 auto; margin:0; padding-right:1rem;"><a href="" style="color:#232323;"></a></p>
    <script src="assets/web/assets/jquery/jquery.min.js">
    </script> 
    <script src="assets/popper/popper.min.js">
    </script> 
    <script src="assets/tether/tether.min.js">
    </script> 
    <script src="assets/bootstrap/js/bootstrap.min.js">
    </script> 
    <script src="assets/smoothscroll/smooth-scroll.js">
    </script> 
    <script src="assets/dropdown/js/nav-dropdown.js">
    </script> 
    <script src="assets/dropdown/js/navbar-dropdown.js">
    </script> 
    <script src="assets/touchswipe/jquery.touch-swipe.min.js">
    </script> 
    <script src="assets/theme/js/script.js">
    </script>
</body>
</html>