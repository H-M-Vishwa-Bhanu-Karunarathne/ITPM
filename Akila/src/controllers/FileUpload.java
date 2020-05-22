package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/uploadFile")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "C:\\Uploads";
    
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        sc.setAttribute("sc", sc.getContextPath());
        
		
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            request.setAttribute("msg", "Incorrect File Formats");
        }
        
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = UPLOAD_DIRECTORY;

        // creates the directory if it does not exist

        File uploadDir = new File(uploadPath);
        System.out.println(uploadDir);
        if (!uploadDir.exists()) {
        	System.out.println("Insied if");
            uploadDir.mkdir();
        }
 
        try {
            // parses the request's content to extract file data
        	RequestContext requestContext = new ServletRequestContext(request);
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(requestContext);
            

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = UPLOAD_DIRECTORY + File.separator + "file.txt";
                        File storeFile = new File(filePath);
                        System.out.println(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("msg", "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/Coupling.jsp");
		requestDispatcher.forward(request, response);
	}

}
