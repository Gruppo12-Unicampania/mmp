package com.gruppo12;

import static com.gruppo12.adding_album.getFileExtension;
import static com.gruppo12.getList.getId;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.List;
import java.util.Random;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class adding_song extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession(false);
        String user = (String) login.getAttribute("user");
        String id;
        id = getId(user);
        String Title;
        Title = "unknown";
        String Singer;
        Singer = "unknown";
        String Composer;
        Composer = "unknown";

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.sendRedirect("song_admin_space");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        int MEMORY_THRESHOLD = 0;
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        String album_name = (String) login.getAttribute("album");
        ServletFileUpload upload = new ServletFileUpload(factory);
        long MAX_FILE_SIZE;
        MAX_FILE_SIZE = 1024 * 1024 * 30;
        upload.setFileSizeMax(MAX_FILE_SIZE);
        long MAX_REQUEST_SIZE;
        MAX_REQUEST_SIZE = 1024 * 1024 * 8;
        upload.setSizeMax(MAX_REQUEST_SIZE);
        try {
            String uploadPath = null;
            @SuppressWarnings("unchecked")
            List<FileItem> formItems;
            formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        String fieldname = item.getFieldName();
                        if (fieldname.equals("Title")) {
                            Title = item.getString();
                        }
                        if (fieldname.equals("Singer")) {
                            Singer = item.getString();
                        }
                        if (fieldname.equals("Composer")) {
                            Composer = item.getString();
                        }

                        uploadPath = getServletContext().getRealPath("") + File.separator + "songs" + File.separator + id + File.separator + album_name + "";
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }

                    } else {
                        Random rand = new Random(System.currentTimeMillis());
                        String fileNameWithExt = new File(item.getName()).getName();
                        String extension = (getFileExtension(fileNameWithExt));
                        String fileNameWithOutExt = FilenameUtils.removeExtension(fileNameWithExt);
                        String serial = Integer.toString(rand.nextInt(906917));
                        String fileName = fileNameWithOutExt + serial + "." + extension;
                        String filePath = uploadPath + File.separator + fileName;
                        String DBPath = "songs" + File.separator + id + File.separator + album_name + "" + File.separator + fileName;
                        if ((getFileExtension(fileName)).equals("mp3") || (getFileExtension(fileName)).equals("wav")) {
                            String Type;
                            Type = getFileExtension(fileName);
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                            DBUpload.uploadSong(DBPath, Title, Composer, Singer, Type, album_name);
                            response.sendRedirect("song_admin_space");
                        }
                        else response.sendRedirect("song_admin_space");
                    }
                }
            }
        } catch (Exception ex) {
            response.sendRedirect("song_admin_space");
        }
    }
}
