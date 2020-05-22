package controllers;

import java.util.ArrayList;

public class Method {
	
	public Method() {
		
	}
	
	String methodName = "";
	String methodType = "";
	int lineNumber;
	
	ArrayList<Line> lines = new ArrayList<Line>();
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
	
	public void detectMethodType(ArrayList<String> lines) {
		
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
}
