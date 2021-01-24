package com.gruppo12;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class DBUpload {
        static String URL = "jdbc:mysql://localhost/mydbs";
        static String USER = "admin";
        static String PASS = "admin";
    static void uploadAlbum(String album_name, String id, String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("INSERT INTO album (album_name, id_etichetta, album_image) values (?, ?, ?)");
            sql.setString(1, album_name);
            sql.setString(2, id);
            sql.setString(3, filePath);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Album uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void uploadSong(String song_path, String title, String composer, String singer, String type, String album_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        song_path = song_path.replace("\\\\", "\\");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("INSERT INTO songs (song_path, Title, Composer, Singer, Type, album_name) values (?, ?, ?, ?, ?, ?)");
            sql.setString(1, song_path);
            sql.setString(2, title);
            sql.setString(3, composer);
            sql.setString(4, singer);
            sql.setString(5, type);
            sql.setString(6, album_name);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Song uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void uploadPlaylist(String id, String playlist_image, String playlist_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("INSERT INTO playlist_container (id_user, pl_image, pl_name) values (?, ?, ?)");
            sql.setString(1, id);
            sql.setString(2, playlist_image);
            sql.setString(3, playlist_name);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Playlist uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void RmSong(String id_song) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("DELETE FROM songs WHERE id_song =?");
            sql.setString(1, id_song);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Playlist uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void deleteUser(String id, String path) {
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement sql;
                sql = conn.prepareStatement("DELETE FROM users WHERE ID =?");
                sql.setString(1, id);
                int row = sql.executeUpdate();
                if (row > 0) {
                    File file = new File(path);
                    try {
                        FileUtils.deleteRecursive(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DBUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("DB uploaded and saved into database");
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }

    static void insertSong(String id_song, String id_user, String id_playlist) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("INSERT INTO song_container (id_user, id_song, id_playlist) values (?, ?, ?)");
            sql.setString(1, id_user);
            sql.setString(2, id_song);
            sql.setString(3, id_playlist);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Playlist uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void Makevip(String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("UPDATE users SET vip = !vip WHERE ID = ? ");
            sql.setString(1, id);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("DB uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void MakeVipUnconditionally(String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("UPDATE users SET vip = 1 WHERE ID = ? ");
            sql.setString(1, id);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("DB uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void Makeadmin(String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("UPDATE users SET admin = !admin WHERE ID = ? ");
            sql.setString(1, id);
            int row = sql.executeUpdate();
            if (row > 0) {
                MakeVipUnconditionally(id);
                System.out.println("DB uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void deleteAlbum(String id, String path, String album_name) {
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement sql;
                sql = conn.prepareStatement("DELETE FROM album WHERE id_etichetta =? AND album_name =?");
                sql.setString(1, id);
                sql.setString(2, album_name);
                int row = sql.executeUpdate();
                if (row > 0) {
                    File file = new File(path);
                    try {
                        FileUtils.deleteRecursive(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DBUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("DB uploaded and saved into database");
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }

    static void RmSongInPlaylist(String id_song, String id_playlist) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("DELETE FROM song_container WHERE id_song =? AND id_playlist =?");

            sql.setString(1, id_song);
            sql.setString(2, id_playlist);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("DB uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void MakeVipReq(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("INSERT INTO vip_request (id_user, vip_request) values (?, ?)");
            sql.setString(1, id);
            sql.setString(2, "1");
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Playlist uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void CleanVip(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("DELETE FROM vip_request WHERE id_user =?");

            sql.setString(1, id);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("DB uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void uploadMidi(String id_song, String id, String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        filePath = filePath.replace("\\\\", "\\");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("INSERT INTO midi (id_song, id_user, midi_path, approved) values (?, ?, ?, 0)");
            sql.setString(1, id_song);
            sql.setString(2, id);
            sql.setString(3, filePath);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Album uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void uploadScore(String id_song, String id, String filePath) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        filePath = filePath.replace("\\\\", "\\");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("INSERT INTO musical_scores (id_song, id_user, path, approved) values (?, ?, ?, 0)");
            sql.setString(1, id_song);
            sql.setString(2, id);
            sql.setString(3, filePath);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("Album uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void approveMidi(String path) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("UPDATE midi SET approved = 1 WHERE midi_path = ? ");
            sql.setString(1, path);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("DB uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    static void approveScore(String path) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement sql;
            sql = conn.prepareStatement("UPDATE musical_scores SET approved = 1 WHERE path = ? ");
            sql.setString(1, path);
            int row = sql.executeUpdate();
            if (row > 0) {
                System.out.println("DB uploaded and saved into database");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
