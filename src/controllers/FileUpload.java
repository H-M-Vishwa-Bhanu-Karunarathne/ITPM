package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import org.json.JSONObject;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/uploadFile")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "C:\\Uploads";
    private static final String WEIGHT_DIRECTORY = "C:\\Weights";
    
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
    
    public void deleteAllFilesAndDirectories(File uploadDir) {
	    for (File file: uploadDir.listFiles()) {
	        if (file.isDirectory())
	        	deleteAllFilesAndDirectories(file);
	        file.delete();
	    }
    	
    }
    
    public JSONObject readWeightsJSON() throws IOException {
    	File weightsFile = new File(WEIGHT_DIRECTORY + File.separator + "weights.json");
    	
    	BufferedReader reader = new BufferedReader(new FileReader(weightsFile));
    	StringBuilder stringBuilder = new StringBuilder();
    	String line = null;
    	String ls = System.getProperty("line.separator");
    	while ((line = reader.readLine()) != null) {
    		stringBuilder.append(line);
    		stringBuilder.append(ls);
    	}
    	// delete the last new line separator
    	stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    	reader.close();

    	String content = stringBuilder.toString();
    	JSONObject weights = new JSONObject(content);
    	return weights;
    }
    
    
    protected void loadWeightsTable(HttpServletRequest request) throws IOException {
    	JSONObject weights = readWeightsJSON();
    	request.setAttribute("ifWeight", weights.getInt("if"));
    	request.setAttribute("whileWeight", weights.getInt("while"));
    	request.setAttribute("switchWeight", weights.getInt("switch"));
    	request.setAttribute("caseWeight", weights.getInt("case"));
    	
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
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        deleteAllFilesAndDirectories(uploadDir);

 
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
                            String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                            File storeFile = new File(filePath);
                            // saves the file on disk
                            item.write(storeFile);
                            request.setAttribute("msg", "Upload has been done successfully!");
                        
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        loadWeightsTable(request);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/Control-Structure.jsp");
		requestDispatcher.forward(request, response);
	}

}
