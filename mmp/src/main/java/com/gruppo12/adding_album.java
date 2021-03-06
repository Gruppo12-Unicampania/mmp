package com.gruppo12;

import static com.gruppo12.getList.getId;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.*;
import com.mysql.jdbc.*;
import java.util.Random;

public class adding_album extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession(false);
        String user = (String) login.getAttribute("user");
        String id;
        String pathtodb = null;
        id = getId(user);
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.sendRedirect("add_album");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        int MEMORY_THRESHOLD = 0;
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        String album_name = "default";
        ServletFileUpload upload = new ServletFileUpload(factory);
        long MAX_FILE_SIZE;
        MAX_FILE_SIZE = 1024 * 1024 * 5;
        upload.setFileSizeMax(MAX_FILE_SIZE);
        long MAX_REQUEST_SIZE;
        MAX_REQUEST_SIZE = 1024 * 1024 * 8;
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = null;
        @SuppressWarnings("unchecked")
        List<FileItem> formItems = null;
        try {
            formItems = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            Logger.getLogger(adding_album.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    String fieldname = item.getFieldName();
                    if (fieldname.equals("album_name")) {
                        album_name = item.getString();
                    }
                    uploadPath = getServletContext().getRealPath("") + "/" + "songs" + File.separator + id + File.separator + album_name + "";
                    pathtodb = "songs" + File.separator + id + File.separator + album_name + "";
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
                    if ((getFileExtension(fileName)).equals("jpg")) {
                        File storeFile = new File(filePath);
                        try {
                            pathtodb = pathtodb + File.separator + fileName;
                            item.write(storeFile);
                            DBUpload.uploadAlbum(album_name, id, pathtodb);
                            response.sendRedirect("album_space");
                        } catch (Exception ex) {
                            response.sendRedirect("album_space");
                        }
                    }
                    else response.sendRedirect("album_space");
                }
            }
        }

    }

    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
