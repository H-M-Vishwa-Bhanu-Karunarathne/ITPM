package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class UpdateWeights
 */
@WebServlet("/UpdateWeights")
public class UpdateWeights extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String WEIGHT_DIRECTORY = "C:\\Weights";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWeights() {
        super();
        // TODO Auto-generated constructor stub
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
    
    protected void writeJsonWeights(String ifWeight, String whileWeight, String switchWeight, String caseWeight) throws IOException {
    	JSONObject readJSON = readWeightsJSON();
    	readJSON.put("if", ifWeight);
    	readJSON.put("elseif", ifWeight);
    	readJSON.put("while", whileWeight);
    	readJSON.put("for", whileWeight);
    	readJSON.put("dowhile", whileWeight);
    	readJSON.put("switch", switchWeight);
    	readJSON.put("case", caseWeight);
    	
    	FileWriter myWriter = new FileWriter(WEIGHT_DIRECTORY + File.separator + "weights.json");
        myWriter.write(readJSON.toString());
        myWriter.close();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		writeJsonWeights(request.getParameter("if"), request.getParameter("while"), request.getParameter("switch"), request.getParameter("case"));
		
		loadWeightsTable(request);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/Control-Structure.jsp");
		requestDispatcher.forward(request, response);
	}

}
