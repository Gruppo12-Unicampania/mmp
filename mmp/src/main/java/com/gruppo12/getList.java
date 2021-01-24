package com.gruppo12;

import static com.gruppo12.DBUpload.PASS;
import static com.gruppo12.DBUpload.URL;
import static com.gruppo12.DBUpload.USER;
import java.io.*;
import static java.lang.System.out;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getList {

    public static List GetPlaylist(String Username)
            throws IOException, NoSuchElementException {
        String id;
        id = getId(Username);
        List dataList;
        dataList = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM playlist_container WHERE id_user=?");
            sql.setString(1, id);
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    counter++;
                    dataList.add(rs.getString("pl_name"));
                    dataList.add(rs.getString("pl_image"));
                }
            } catch (Exception e) {
                if (counter == 0) {
                    dataList.add("Nessun elemento");
                    dataList.add("image/default.jpg");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                dataList.add("Nessun elemento");
                dataList.add("image/default.jpg");
                return dataList;
            }
        }
        return dataList;
    }

    public static String getId(String Username) {
        List idlist;
        idlist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT ID FROM users WHERE Username=?");
            sql.setString(1, Username);
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    idlist.add(rs.getInt("ID"));
                }
            } catch (Exception e) {
                out.println("Query Error!");
                return null;
            }
            Iterator itr;
            for (itr = idlist.iterator(); itr.hasNext();) {
                return itr.next().toString();
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Db Error!");
            return null;
        }
        return (null);
    }

    public static List getSonginPlaylist(String pl, String Username) {
        String id;
        id = getId(Username);
        String id_playlist;
        id_playlist = getPlaylistId(id, pl);
        List songlist;
        songlist = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM song_container JOIN songs ON song_container.id_song = songs.id_song WHERE id_user= ? AND id_playlist= ?");
            sql.setString(1, id);
            sql.setString(2, id_playlist);
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    counter++;
                    songlist.add(rs.getString("Title"));
                    songlist.add(rs.getInt("id_song"));
                }
            } catch (Exception e) {
                if (counter == 0) {
                    songlist.add("Errore DB");
                    songlist.add("Errore DB2");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                songlist.add("Nessun elemento");
                songlist.add("-");
                return songlist;
            }
        }
        return songlist;
    }

    public static String getPlaylistImage(String id_user, String pl) {
        List pllist;
        pllist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT pl_image FROM playlist_container WHERE id_user=? AND pl_name=?");
            sql.setString(1, id_user);
            sql.setString(2, pl);

            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    pllist.add(rs.getString("pl_image"));
                }
            } catch (Exception e) {
                out.println("Query Error!");
                return null;
            }
            Iterator itr;
            for (itr = pllist.iterator(); itr.hasNext();) {
                return itr.next().toString();
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Db Error!");
            return null;
        }
        return (null);
    }

    public static String getPlaylistId(String id_user, String pl) {
        List pllist;
        pllist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT idplaylist FROM playlist_container WHERE id_user=? AND pl_name=?");
            sql.setString(1, id_user);
            sql.setString(2, pl);

            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    pllist.add(rs.getInt("idplaylist"));
                }
            } catch (Exception e) {
                out.println("Query Error!");
                return null;
            }
            Iterator itr;
            for (itr = pllist.iterator(); itr.hasNext();) {
                return itr.next().toString();
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Db Error!");
            return null;
        }
        return (null);
    }

    public static List GetAlbum(String Username)
            throws IOException, NoSuchElementException {
        String id;
        id = getId(Username);
        List dataList;
        dataList = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM album WHERE id_etichetta=?");
            sql.setString(1, id);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("album_name"));
                dataList.add(rs.getString("album_image"));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                dataList.add("Nessun elemento");
                dataList.add("image/default.jpg");
            }
        }
        return dataList;
    }

    public static List GetSongsRm(String Username, String Album)
            throws IOException, NoSuchElementException {
        String id;
        id = getId(Username);
        List dataList;
        dataList = new ArrayList();
        String toquery = "\"" + Album + "\"";
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn;
        String myquery = "SELECT * FROM songs WHERE album_name=" + toquery;

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement(myquery);
            ResultSet rs;
            rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("Title"));
                dataList.add(rs.getString("id_song"));
            }
            conn.close();
        } catch (SQLException ex) {
            dataList.add("Errore in lettura");
        }
        return dataList;

    }

    public static List GetSongs(String Username, String Album)
            throws IOException, NoSuchElementException {
        String id;
        id = getId(Username);
        List dataList;
        dataList = new ArrayList();
        String toquery = "\"" + Album + "\"";
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn;
        String myquery = "SELECT * FROM songs WHERE album_name=" + toquery;

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement(myquery);
            ResultSet rs;
            rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("Title"));
            }
            conn.close();
        } catch (SQLException ex) {
            dataList.add("Errore in lettura");
        }
        return dataList;

    }

    public static List GetSongsSearch(String name)
            throws IOException, NoSuchElementException {
        List datalist;
        datalist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = null;
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;

            if (name != null && name.length() > 0) {
                sql = conn.prepareStatement("SELECT Title,Composer,Singer,album_name,id_song FROM songs WHERE Title like '" + '%' + name + '%' + "'");
                rs = sql.executeQuery();
            } else {
                sql = conn.prepareStatement("SELECT Title,Composer,Singer,album_name,id_song FROM songs WHERE Title like '" + '%' + "name" + '%' + "'");
                rs = sql.executeQuery();
            }
            ResultSetMetaData metadata = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    datalist.add(rs.getString(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(search_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datalist;
    }

    public static List GetUsers()
            throws IOException, NoSuchElementException {
        List dataList;
        dataList = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM users WHERE ID NOT LIKE 1");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("Username"));
                dataList.add(rs.getString("admin"));
                dataList.add(rs.getString("ID"));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                dataList.add("Nessun elemento");
                dataList.add("DUMMYID");
                return dataList;
            }
        }
        return dataList;
    }

    public static List GetVipUsers()
            throws IOException, NoSuchElementException {
        List dataList;
        dataList = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM users JOIN vip_request ON vip_request.id_user=users.ID WHERE vip_request=1");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("Username"));
                dataList.add(rs.getString("admin"));
                dataList.add(rs.getString("ID"));
                dataList.add(rs.getString("vip"));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                dataList.add("Nessun elemento");
                dataList.add("DUMMYID");
                return dataList;
            }
        }
        return dataList;
    }

    public static List GetAdminUsers()
            throws IOException, NoSuchElementException {
        List dataList;
        dataList = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM users WHERE ID NOT LIKE 1");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("Username"));
                dataList.add(rs.getString("admin"));
                dataList.add(rs.getString("ID"));
                dataList.add(rs.getString("vip"));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                dataList.add("Nessun elemento");
                dataList.add("DUMMYID");
                return dataList;
            }
        }
        return dataList;
    }

    static String getPathFromId(String id_song) {
        List pathlist;
        pathlist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT song_path FROM songs WHERE id_song=?");
            sql.setString(1, id_song);
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    pathlist.add(rs.getString("song_path"));
                }
            } catch (Exception e) {
                out.println("Query Error!");
                return null;
            }
            Iterator itr;
            for (itr = pathlist.iterator(); itr.hasNext();) {
                return itr.next().toString();
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Db Error!");
            return null;
        }
        return (null);

    }

    public static String isVip(String user) {
        List idlist;
        idlist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT vip FROM users WHERE Username=?");
            sql.setString(1, user);
            try (ResultSet rs = sql.executeQuery()) {
                while (rs.next()) {
                    idlist.add(rs.getString("vip"));
                }
            } catch (Exception e) {
                out.println("Query Error!");
                return "error1";
            }
            Iterator itr;
            for (itr = idlist.iterator(); itr.hasNext();) {
                return itr.next().toString();
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Db Error!");
            return "error2";
        }
        return user;
    }

    public static List GetPendingMidi(String id_etichetta) {
        List dataList;
        dataList = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM midi_view2 WHERE id_etichetta=? ");
            sql.setString(1, id_etichetta);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("Title"));
                dataList.add(rs.getString("midi_path"));
                dataList.add(rs.getString("midi_path"));

            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                dataList.add("Nessun elemento");
                dataList.add("DUMMYID");
                dataList.add("DUMMYID");
                return dataList;
            }
        }
        return dataList;
    }

    public static List GetPendingScores(String id_etichetta) {
        List dataList;
        dataList = new ArrayList();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("SELECT * FROM musical_scores_view2 WHERE id_etichetta=? ");
            sql.setString(1, id_etichetta);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                dataList.add(rs.getString("Title"));
                dataList.add(rs.getString("path"));
                dataList.add(rs.getString("path"));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                dataList.add("Nessun elemento");
                dataList.add("DUMMYID");
                dataList.add("DUMMYID");
                return dataList;
            }
        }
        return dataList;
    }

    public static List GetSongsMidiSearch(String name)
            throws IOException, NoSuchElementException {
        List datalist;
        datalist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = null;
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;

            if (name != null && name.length() > 0) {
                sql = conn.prepareStatement("SELECT Title,Composer,album_name,midi_path FROM approved_midi WHERE Title like '" + '%' + name + '%' + "'");
                rs = sql.executeQuery();
            } else {
                sql = conn.prepareStatement("SELECT Title,Composer,album_name,midi_path FROM approved_midi WHERE Title like '" + '%' + name + '%' + "'");
                rs = sql.executeQuery();
            }
            ResultSetMetaData metadata = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    datalist.add(rs.getString(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(search_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datalist;
    }

    public static List GetSongsScoresSearch(String name)
            throws IOException, NoSuchElementException {
        List datalist;
        datalist = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = null;
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;

            if (name != null && name.length() > 0) {
                sql = conn.prepareStatement("SELECT Title,Composer,album_name,path FROM approved_scores WHERE Title like '" + '%' + name + '%' + "'");
                rs = sql.executeQuery();
            } else {
                sql = conn.prepareStatement("SELECT Title,Composer,album_name,path FROM approved_scores WHERE Title like '" + '%' + name + '%' + "'");
                rs = sql.executeQuery();
            }
            ResultSetMetaData metadata = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    datalist.add(rs.getString(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(search_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datalist;
    }

    public static List GetSongsInPl(String id_pl)
            throws IOException, NoSuchElementException {
        List<Song> canzoni = new ArrayList<>();
        int counter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("select `mydbs`.`songs`.`song_path` AS `link_url`, `mydbs`.`album`.`album_image` AS `cover`,`mydbs`.`songs`.`Title` AS `name`,`mydbs`.`song_container`.`id_playlist` AS `id_playlist`,`mydbs`.`songs`.`album_name` AS `album`,`mydbs`.`songs`.`Composer` AS `composer`,`mydbs`.`songs`.`Singer` AS `singer` from (`mydbs`.`song_container` join `mydbs`.`songs` join `mydbs`.`album`) where `mydbs`.`songs`.`id_song` = `mydbs`.`song_container`.`id_song` and `mydbs`.`songs`.`album_name`=`album`.`album_name` and `id_playlist` = ?");
            sql.setString(1, id_pl);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                String link_url = rs.getString("link_url");
                String cover = rs.getString("cover");
                String name = rs.getString("name");
                String album = rs.getString("album");
                String composer = rs.getString("composer");
                String singer = rs.getString("singer");
                canzoni.add(new Song(link_url, cover, name, album, composer, singer));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                canzoni.add(new Song("error", "error", "error", "error", "error", "error"));
                return canzoni;
            }
        }
        return canzoni;
    }
    public static String GetJson(String id,String id_song){
           int counter = 0;        
        JSONArray ja = new JSONArray();
        JSONObject jo[] = new JSONObject[1200];

        String singer;
        String type;
        String composer;
        String link_url;
        String cover;
        String name;
        String album;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement sql;
            sql = conn.prepareStatement("(SELECT * FROM vista_pl WHERE id_playlist= ? AND id_song= ?) UNION (SELECT * FROM vista_pl WHERE id_playlist= ? AND id_song NOT LIKE ? ORDER BY RAND())");
            sql.setString(1, id);
            sql.setString(2, id_song);
            sql.setString(3, id);
            sql.setString(4, id_song);

            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                counter++;
                jo[counter] = new JSONObject();
                link_url = rs.getString("link_url");
                link_url = link_url.replace("\\", "/");
                cover = rs.getString("cover");
                cover = cover.replace("\\", "/");
                name = rs.getString("name");
                album = rs.getString("album");
                composer = rs.getString("composer");
                singer = rs.getString("singer");
                type = rs.getString("type");
                jo[counter].put("name", name);
                jo[counter].put("composer", composer);
                jo[counter].put("lyrics", "");
                jo[counter].put("singer", singer);
                jo[counter].put("album", album);
                jo[counter].put("type", type);
                jo[counter].put("cover", cover);
                jo[counter].put("link_url", link_url);

                ja.add(jo[counter]);

            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            if (counter == 0) {
                String result = "@Produces(\"application/json\") Output: \n\nF to ERROR Output: \n\n" + id;
                return id;

            }
        }
    String result = ja.toString();
    return result;}
}
