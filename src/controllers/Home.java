package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // location to store file uploaded
    private static final String WEIGHT_DIRECTORY = "C:\\Weights";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        sc.setAttribute("sc", sc.getContextPath());
        
        createWeightsFile();
               
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/index.jsp");
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
