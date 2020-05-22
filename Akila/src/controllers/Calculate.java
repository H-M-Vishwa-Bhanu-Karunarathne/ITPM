package controllers;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Servlet implementation class Calculate
 */
@WebServlet("/Calculate")
public class Calculate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    // location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "C:\\Uploads";
	
	// Lists
	private ArrayList<String> importedNonBuiltInClasses = new ArrayList<String>();
	private ArrayList<String> methodCurlyBraces = new ArrayList<String>();
	private ArrayList<Method> methodsList = new ArrayList<Method>();
	private ArrayList<String> methodNameList = new ArrayList<String>();
	private ArrayList<String> globalVariables = new ArrayList<String>();
	private ArrayList<ComplexityLine> compLines = new ArrayList<ComplexityLine>();
    
	
	//Booleans
	boolean isClassNameDetected = false;
	boolean isInsideMethod = false;
	
	
	//String Variables
	String className = "";
	
	int lineNumber = 0;
	
	
	Method method = null;
	Weights weights = new Weights();
   
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calculate() {
        super();
       
    }
    
    protected void readImportedNonBuiltInClasses(String line) {
    	if (line.matches("\\s*import\\s+(?!java).*;\\s*")) {
    		String[] splitedArray = line.split("\\.");
    		String clz = splitedArray[splitedArray.length -1];
    		clz = clz.replace(";", "");
    		importedNonBuiltInClasses.add(clz);
    	}
    }
    
    protected void detectClassName(String line) {
    	if (line.matches("\\s*(public\\s+)?class\\s+.*\\s*(\\{)?") && isClassNameDetected == false) {
    		isClassNameDetected = true;		
    		String words[] = line.replaceAll(" +", " ").split(" ");
    		int indexOfClassKeyWord = 0;
    		
    		for (int i = 0; i < words.length; i++) {
    			if (words[i].equals("class")) {
    				indexOfClassKeyWord = i;
    				break;
    			}
    		}
    		
    		System.out.println("Class Name: "  + words[indexOfClassKeyWord + 1]);
    		className = words[indexOfClassKeyWord + 1];
    	}
    }
    
    void detectMethod(String line) {
    	boolean isMethodFound = true;
    	line = line.replaceAll(" +", " ");
		line = line.replaceAll("\\(", " (");
		String words[] = line.split(" ");
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i];

			if (word.equals("if") || word.equals("else") || word.equals("while") || word.equals("switch") || word.equals(className)) {
				isMethodFound = false;
				break;
			}
		}

    	if (isMethodFound && line.matches("\\s*([a-zA-Z]*\\s+)?[a-zA-Z]*\\s+[a-zA-Z]*\\s*\\(.*\\)(\\s*[a-zA-Z]+\\s+.+)?\\s*(\\{)?")) {
    		method = new Method();

    		if (isMethodFound) {  			
    			String firstHalf[] = (line.split("\\(")[0]).split(" ");
    			String methodName = firstHalf[firstHalf.length -1];
    			method.setLineNumber(lineNumber);
    			method.methodName = methodName;
    			isInsideMethod = true;
 
    		}
    		
    	} 
    	
    	if (isInsideMethod) {
    		if (line.indexOf("{") > -1) {
    			methodCurlyBraces.add("{");
    		}
    		
    		if (line.indexOf("}") > -1) {
    			methodCurlyBraces.remove(methodCurlyBraces.size() - 1);
    		}
    		
    		if (methodCurlyBraces.isEmpty()) {
    			isInsideMethod = false;
    			methodsList.add(method);
    			methodNameList.add(method.getMethodName());
    		}
    		
    		Line methodLine = new Line();
    		methodLine.setLineNumber(lineNumber);
    		methodLine.setLine(line);
    		method.lines.add(methodLine);
    	}
    	
    }
    
    protected void detectGlobalVariables(String line) {
    	String splitedByEqualMark = line;

    	if (line.indexOf("=") > -1) {
    		splitedByEqualMark = line.split("=")[0];
    	}
    	
    	splitedByEqualMark = splitedByEqualMark.replaceAll(";", "");
    	if (splitedByEqualMark.matches("\\s*([a-zA-Z]*\\s*)?(static\\s*)?\\s*(final\\s*)?\\s*[a-zA-z]*(\\<[a-zA-Z]*\\>)?\\s*[a-zA-Z0-9]*\\s*") && !isInsideMethod) {
    		
    		splitedByEqualMark = splitedByEqualMark.replaceAll(" +", " ");
    		String[]  words = splitedByEqualMark.split(" ");
    		
    		globalVariables.add(words[words.length -1]);
    	}
    	 	
    	
    }
    
    
    
    protected String lineSeparator() throws IOException {
		File file = new File(UPLOAD_DIRECTORY + File.separator + "file.txt"); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String line; 
		
		while ((line = br.readLine()) != null) {
			if (!line.matches("\\s*")) {
				ComplexityLine cl = new ComplexityLine();
				lineNumber++;
				cl.setLine(line.trim());
				cl.setLineNumber(lineNumber);
				compLines.add(cl);


				readImportedNonBuiltInClasses(line.trim());
				detectClassName(line.trim());
				detectMethod(line.trim()); 
				detectGlobalVariables(line.trim());
			}
		}
		
		for (Method method: methodsList) {

             
             method.setMethodType("regular");

             ArrayList<Line> methodLines = method.getLines();
             
             for (int i = 1 ; i < methodLines.size(); i++) {
            	 Line methodLine = methodLines.get(i);
            	 String[] lineWords = methodLine.getLine().replaceAll(" +", " ").split(" ");
            	 for (int j = 0; j < lineWords.length; j++) {
            		 if (lineWords[j].equals(method.getMethodName())) {
            			 method.setMethodType("recursive");
            		 }
            	 }
             }
 
        }
		
		for (String globalVar: globalVariables) {

		}
				
		return "";
    }
    
    protected void calculatingComplexity() {
    	for (Method method: methodsList) {
//    		System.out.println(method.getLines());
    		ArrayList<Line> methodLines = method.getLines();
    		System.out.println(method.getMethodName() + "-" + method.getMethodType());
    		for (int i = 1; i < methodLines.size(); i++) {
    			int lineNo = methodLines.get(i).getLineNumber() - 1;
    			String line = methodLines.get(i).getLine();
//    			System.out.println(line);
    			
    			for (int j = 0; j < methodsList.size(); j++) {
    				if (!method.getMethodName().equals(methodsList.get(j).getMethodName()) && line.indexOf(methodsList.get(j).getMethodName()) > -1) {
    					
    					if (method.getMethodType().equals("regular")) {
    						
    						if (methodsList.get(j).getMethodType().equals("regular")) {
    							ComplexityLine compline = compLines.get(lineNo);
    							compline.setNmcms(1);
    							compline.setLineComplexity(compline.wmcms * compline.getNmcms());
    						}
    						
							if (methodsList.get(j).getMethodType().equals("recursive")) {
    							ComplexityLine compline = compLines.get(lineNo);
    							compline.setNmcrms(1);
    							compline.setLineComplexity(compline.wmcrms * compline.getNmcrms());
    						}
    					}
    					
    					if (method.getMethodType().equals("recursive")) {

    						if (methodsList.get(j).getMethodType().equals("regular")) {
    							ComplexityLine compline = compLines.get(lineNo);
    							compline.setNrmcms(1);
    							compline.setLineComplexity(compline.wrmcms * compline.getNrmcms());
    						}
    						
							if (methodsList.get(j).getMethodType().equals("recursive")) {
    							ComplexityLine compline = compLines.get(lineNo);
    							compline.setNrmcrms(1);
    							compline.setLineComplexity(compline.wrmcrms * compline.getNrmcrms());
    						}
    					}
    				}
    				
    				if (method.getMethodName().equals(methodsList.get(j).getMethodName()) && method.getMethodType().equals("recursive") && line.indexOf(methodsList.get(j).getMethodName()) > -1) {
    						
						ComplexityLine compline = compLines.get(lineNo);
						compline.setNr(1);
						compline.setLineComplexity(compline.wr * compline.getNr());
    					
    				}
    			}
    			
    		}
    	}
    	
    	for (Method method: methodsList) {
 
    		ArrayList<Line> methodLines = method.getLines();
    		System.out.println(method.getMethodName() + "-" + method.getMethodType());
    		for (int i = 1; i < methodLines.size(); i++) {
    			int lineNo = methodLines.get(i).getLineNumber() - 1;
    			String line = methodLines.get(i).getLine();
//    			System.out.println(line);
    			
    			for (int j = 0; j < globalVariables.size(); j++) {
    				if (line.indexOf(globalVariables.get(j)) > -1) {
    					
    					if (method.getMethodType().equals("regular")) {
    						
							ComplexityLine compline = compLines.get(lineNo);
							compline.setNmrgvs(1);
							compline.setLineComplexity(compline.wmrgvs * compline.getNmrgvs());

    					}
    					
    					if (method.getMethodType().equals("recursive")) {
    						
							ComplexityLine compline = compLines.get(lineNo);
							compline.setNrmrgvs(1);
							compline.setLineComplexity(compline.wrmcrms * compline.getNmrgvs());

    					}
    				}
    			}
    			
    		}
    	}
    
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
		
        lineSeparator();
        calculatingComplexity();
        request.setAttribute("compObjs", compLines);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/Coupling.jsp");
		requestDispatcher.forward(request, response);
	}

}
