/**
 * servlet to handle file upload requests
 * 
 * @author hturksoy
 * 
 */
package com.owb.playhelp.server.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileUploadServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7051474454638826257L;
	private static final String UPLOAD_DIRECTORY = "/home/mcharcos/testowb/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	
	// process only multipart requests
	if (ServletFileUpload.isMultipartContent(req)) {

	    // Create a factory for disk-based file items
	    FileItemFactory factory = new DiskFileItemFactory();

	    // Create a new file upload handler
	    ServletFileUpload upload = new ServletFileUpload(factory);

	    // Parse the request
	    try {
		@SuppressWarnings("unchecked")
		List<FileItem> items = upload.parseRequest(req);
		for (FileItem item : items) {
		    // process only file upload - discard other form item types
		    if (item.isFormField()) continue;
		    
		    String fileName = item.getName();
		    // get only the file name not whole path
		    if (fileName != null) {
			fileName = FilenameUtils. getName(fileName);
		    }

		    File uploadedFile = new File(UPLOAD_DIRECTORY, fileName);
		    if (uploadedFile.createNewFile()) {
			item.write(uploadedFile);
			resp.setStatus(HttpServletResponse.SC_CREATED);
			resp.getWriter().print("The file was created successfully.");
			resp.flushBuffer();
		    } else
			throw new IOException("The file already exists in repository.");
		}
	    } catch (Exception e) {
		resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			"An error occurred while creating the file : " + e.getMessage());
	    }

	} else {
	    resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
			    "Request contents type is not supported by the servlet.");
	}
    }
}