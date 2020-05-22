package controllers;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import controllers.ComplexityLine;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;


/**
 * Servlet implementation class Calculate
 */
@WebServlet("/Calculate")
public class Calculate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    // location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "C:\\Uploads";
	private static final String WEIGHT_DIRECTORY = "C:\\Weights";
    
    private int Ccspps = 0;
    private int gblCumComplex = 0;
    private String CS = "none";
    private String prevCS = "none";
    private int NoOfConds = 0; // Number of conditions
    private int lastActiveCcspps = 0;
    private int gblLoc = 0;
  
    private boolean isInsideCS = false; // Check whether we are inside a control structure
    
    private JsonObject weights;
    private String strWeights;
    
    public ArrayList<ComplexityLine> complexityObjs;
    public ArrayList<String> fileNameList = new ArrayList<String>();
    public ArrayList<ComplexityFile> fileObjList = new ArrayList<ComplexityFile>();
    
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public Calculate() throws IOException {
        super();   
        loadWeights();    
    }
    
    public void loadWeights() throws IOException {
    	JSONObject weightsFromFile = readWeightsJSON();
        
        weights = new JsonObject();
        weights.addProperty("if", weightsFromFile.getInt("if"));
        weights.addProperty("elseif", weightsFromFile.getInt("elseif"));
        weights.addProperty("else", 0);
        weights.addProperty("for", weightsFromFile.getInt("for"));
        weights.addProperty("while", weightsFromFile.getInt("while"));
        weights.addProperty("dowhile", weightsFromFile.getInt("dowhile"));
        weights.addProperty("switch", weightsFromFile.getInt("switch"));
        weights.addProperty("case", weightsFromFile.getInt("case"));
        weights.addProperty("none", 0);

        strWeights = weights.toString();
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
    
    public void deleteZipFile(File uploadDir) {
	    for (File file: uploadDir.listFiles()) {
	        if (file.getName().endsWith(".zip")) {
		        file.delete();
	        }

	    }
    }
    
    public void readAllFiles(File uploadDir) {
    	for (File file: uploadDir.listFiles()) {
	        if (file.isDirectory()) {
	        	readAllFiles(file);
	        } else {
		        String fullPath = file.getPath();
		        fileNameList.add(fullPath);
	        }

	    }
    	System.out.println("Called");
    }
    
    protected boolean detectEndingCurlyBrace(String line) {
    	return line.indexOf("}") > -1;
    }
    
    protected boolean detectOpeningCurlyBrace(String line) {
    	return line.indexOf("{") > -1;
    }
    
    protected void DetectCurlyBracesAndNest(String line) {
    	JsonObject jsonWeights = new Gson().fromJson(strWeights, JsonObject.class);
    	
		if (detectOpeningCurlyBrace(line) && detectEndingCurlyBrace(line)) {
			// No Level up or down of Ccspps
		} else if (detectOpeningCurlyBrace(line) && isInsideCS) {
//			Ccspps =  Ccspps + jsonWeights.get(CS).getAsInt();
		} else if (detectEndingCurlyBrace(line) && isInsideCS) {
//			Ccspps = Ccspps - jsonWeights.get(prevCS).getAsInt();
		}
		
		if (Ccspps == 0 && isInsideCS == true) {
			isInsideCS = false;
		}
		
		if (CS.equals("else")) {
//			Ccspps = Ccspps - 2;
		}
    }
    
    protected int countOperator(String line, String logicalOperator) {
    	int count = 0;
    	
    	Pattern pattern = Pattern.compile(logicalOperator);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            count++;
        }    

    	return count;
    }
    
    protected int countNumberOfConditions(String line) {
    	int logAND = countOperator(line, "[^&]&&[^&]");
    	int bitAND = countOperator(line, "[^&]&[^&]");
    	int logOR = countOperator(line, "[^\\|]\\|\\|[^\\|]");
    	int bitOR = countOperator(line, "[^\\|]\\|[^\\|]");
    	    
    	int totCount = logAND + bitAND + logOR + bitOR + 1;
    	
    	return totCount;
    }
    
    protected String detectSwitchCase(String line) {
    	String cs = "";
    	
    	if (line.matches("\\s*switch\\s*\\(.*\\)\\s*\\{?\\s*")) { // Detect Switch
    		NoOfConds = countNumberOfConditions(line);
    		cs = "switch";
    	} else if (line.matches("\\s*case\\s*.*:")) {
    		cs = "case";
    		NoOfConds = 1;
    	} else {
    		cs = "none";
    	}
    	
    	if (!cs.equals("none")) {
    		isInsideCS = true;
    	}
    	
        return cs;
    }
    
    protected String detectWhileLoop(String line) {
    	String cs = "";
    	
    	if (line.matches("\\s*while\\s*\\(.*\\)\\s*\\{?\\s*")) { // Detect While loop
    		NoOfConds = countNumberOfConditions(line);
    		cs = "for";
    	} else if (line.matches("\\s*while\\s*\\(.*\\)\\s*;")) { // Detect Do While
    		cs = "dowhile";	
    		NoOfConds = countNumberOfConditions(line);
    	} else {
    		cs = "none";
    	}
    	
    	if (!cs.equals("none")) {
    		isInsideCS = true;
    	}
    	
        return cs;
    }
    
    protected String detectforLoop(String line) {
    	String cs = "";
    	
    	if (line.matches("\\s*for\\s*\\(.*\\)\\s*\\{?\\s*")) { // Detect For loop
    		NoOfConds = countNumberOfConditions(line);
    		cs = "for";
    	} else {
    		cs = "none";
    	}
    	
    	if (!cs.equals("none")) {
    		isInsideCS = true;
    	}
    	
        return cs;
    }
    
    protected String detectIfElse(String line) {
    	String cs = "";	
    	
    	if (line.matches("\\s*if\\s*\\(.*\\)\\s*\\{?\\s*")) { // Detect If
    		NoOfConds = countNumberOfConditions(line);
     		cs = "if";
     	} else if (line.matches("\\s*\\}?\\s*else\\s*\\{?\\s*")) { // Detect Else
    		cs = "else";
    	} else if (line.matches("\\s*\\}?\\s*else\\s{1}if\\s*\\(.*\\)\\s*\\{?\\s*")) { // Detect Else if
    		NoOfConds = countNumberOfConditions(line);
    		cs = "elseif";
    	} else {
    		cs = "none";
    	}
    	
    	if (!cs.equals("none")) {
    		isInsideCS = true;
    	}
    	
       return cs;
    }
    
    protected void DetectCS(String line) {
    	String controlStruct = "";
    	
    	controlStruct = detectIfElse(line);
    	
    	if (controlStruct.equals("none")) {
    		controlStruct = detectforLoop(line);
    	}
    	
    	if (controlStruct.equals("none")) {
    		controlStruct = detectWhileLoop(line);
    	}
    	
    	if (controlStruct.equals("none")) {
    		controlStruct = detectSwitchCase(line);
    	}
    	
    	CS = controlStruct;
        this.DetectCurlyBracesAndNest(line);
    }
    
    protected ArrayList<ComplexityLine> lineSeparator(String fileName) throws IOException {
		File file = new File(fileName); 
		int loc = 0;
		int cumComplex = 0;
		Ccspps = 0;
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr); 
		  
		String line; 
		complexityObjs = new ArrayList<ComplexityLine>();
		
		while ((line = br.readLine()) != null) {
			if (!line.matches("\\s*")) {
				loc++;
				JsonObject jsonWeights = new Gson().fromJson(strWeights, JsonObject.class);
				ComplexityLine compLine = new ComplexityLine();
				NoOfConds = 0;

				DetectCS(line.trim());
				
				int weight = jsonWeights.get(CS).getAsInt();
				compLine.setLine(line);
				
				compLine.setNumberOfConds(NoOfConds);
				compLine.setWeight(weight);
				
				if (weight <=0) {
					compLine.setPrevCcspps(0);
				} else {
					compLine.setPrevCcspps(Ccspps);
				}
				
				if (weight <= 0) {
					compLine.CalculateLineComplexity(weight, NoOfConds, 0);
				} else {
					compLine.CalculateLineComplexity(weight, NoOfConds, Ccspps);
				}
			
				complexityObjs.add(compLine);
//				System.out.println(line + " " + "weight=" + jsonWeights.get(CS).getAsInt() + " " + "NoOfConds-" + NoOfConds + " " + "Ccpss-" + Ccspps + " LastActive=" + lastActiveCcspps);
				
//				if (!CS.equals("none")) {
//					prevCS = CS;
//				}		
				
				
				if (compLine.getLineComplexity() > 0 && !CS.equals("case")) {
					Ccspps = compLine.getLineComplexity();
				}
				

				cumComplex += compLine.getLineComplexity();
				compLine.setCumCcspps(cumComplex);
				
			}
		}
		
		gblLoc = loc;
		gblCumComplex = cumComplex;
		br.close();
		fr.close();
		return complexityObjs;
    }
    
    protected void unZipFile() throws IOException {
    	String fileName = getFileName();
    	String source = fileName;
    	String destination = UPLOAD_DIRECTORY;   

    	if (fileName.endsWith(".zip")) {
        	try {
        	    ZipFile zipFile = new ZipFile(source);
        	    zipFile.extractAll(destination);
        	} catch (ZipException e) {
        	    e.printStackTrace();
        	}
        	
        	File uploadDir = new File(UPLOAD_DIRECTORY); 
        	
        	deleteZipFile(uploadDir);
    	}
    }
    
    protected String getFileName() {
    	String fileName = "";

    	File folder = new File(UPLOAD_DIRECTORY);
    	File[] listOfFiles = folder.listFiles();

    	for (int i = 0; i < listOfFiles.length; i++) {
    	  if (listOfFiles[i].isFile()) {
    	    fileName = listOfFiles[i].getName();
    	  }
    	}
    	return UPLOAD_DIRECTORY + folder.separator + fileName;
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
		loadWeights();
		File uploadDir = new File(UPLOAD_DIRECTORY);
		Ccspps = 0;
		boolean isSingleFile = true;
		fileNameList.clear();
		fileObjList.clear();

        ServletContext sc = request.getServletContext();
        sc.setAttribute("sc", sc.getContextPath());
		
        ArrayList<ComplexityLine> compObjs = new ArrayList<ComplexityLine>();
        String fileName = getFileName();
        
        if (fileName.endsWith(".zip")) {
        	unZipFile();
        		
        } 
         
        readAllFiles(uploadDir);
        System.out.println(fileNameList);
        
        if (fileNameList.size() != 1) {
        	isSingleFile = false;
        	for(String file: fileNameList) {
            	compObjs = this.lineSeparator(file);
            	ComplexityFile cf = new ComplexityFile();
            	cf.setFileName(file);
            	cf.setLoc(gblLoc);
            	cf.setTotComplexity(gblCumComplex);
            	fileObjList.add(cf);
            }
        	
        	request.setAttribute("compFiles", fileObjList);
        } else {
        	compObjs = this.lineSeparator(fileName);
            request.setAttribute("compObjs", compObjs);
        }
         
        request.setAttribute("isSingleFile", isSingleFile);
        loadWeightsTable(request);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/Control-Structure.jsp");
		requestDispatcher.forward(request, response);
	}

}
