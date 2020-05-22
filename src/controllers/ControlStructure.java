package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class ControlStructure
 */
@WebServlet("/ControlStructure")
public class ControlStructure extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";
    private static final String WEIGHT_DIRECTORY = "C:\\Weights";
    
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlStructure() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void createWeightsFile() throws IOException {
	  	
        File weightDir = new File(WEIGHT_DIRECTORY);
        if (!weightDir.exists()) {
        	weightDir.mkdir();
        }
        
        File weightsFile = new File(WEIGHT_DIRECTORY + File.separator + "weights.json");
    	
    	System.out.println(weightsFile);
    	weightsFile.delete();
    	
        if (weightsFile.createNewFile()) {
          System.out.println("File created: " + weightsFile.getName());
        } else {
          System.out.println("File already exists.");
        }
        
        FileWriter myWriter = new FileWriter(WEIGHT_DIRECTORY + File.separator + "weights.json");
        myWriter.write("{\r\n" + 
        		"  \"if\": 2,\r\n" + 
        		"  \"elseif\": 2,\r\n" + 
        		"  \"for\": 3,\r\n" + 
        		"  \"while\": 3,\r\n" + 
        		"  \"dowhile\": 3,\r\n" + 
        		"  \"switch\": 2,\r\n" + 
        		"  \"case\": 1\r\n" + 
        		"}");
        myWriter.close();
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
        ServletContext sc = request.getServletContext();
        sc.setAttribute("sc", sc.getContextPath());
        
        createWeightsFile();
        loadWeightsTable(request);
        
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/Control-Structure.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
