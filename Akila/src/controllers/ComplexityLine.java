package controllers;

public class ComplexityLine {
	private int lineNumber = 0;
	String line = "";
	
	public final int wr = 2; // Recursive call
	public final int wmcms = 2; // Regular method calling regular method
	public final int wmcrms = 3;// Regular method calling recursive method
	public final int wrmcrms = 4; // Recursive method calling recursive method
	public final int wrmcms = 3;  // Recursive method calling regular method
	public final int wmrgvs = 1; // Regular method referring global variable
	public final int wrmrgvs = 1; // Recursive  method referring global variable
	
	int nr = 0;
	int nmcms = 0; 
	int nmcrms = 0;
	int nrmcrms = 0; 
	int nrmcms = 0;  
	int nmrgvs = 0; 
	int nrmrgvs = 0; 
	
	int lineComplexity;

	public int getWmcms() {
		return wmcms;
	}

	public int getWmcrms() {
		return wmcrms;
	}

	public int getWrmcrms() {
		return wrmcrms;
	}

	public int getWrmcms() {
		return wrmcms;
	}

	public int getWmrgvs() {
		return wmrgvs;
	}

	public int getWrmrgvs() {
		return wrmrgvs;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getNmcms() {
		return nmcms;
	}

	public void setNmcms(int nmcms) {
		this.nmcms = nmcms;
	}

	public int getNmcrms() {
		return nmcrms;
	}

	public void setNmcrms(int nmcrms) {
		this.nmcrms = nmcrms;
	}

	public int getNrmcrms() {
		return nrmcrms;
	}

	public void setNrmcrms(int nrmcrms) {
		this.nrmcrms = nrmcrms;
	}

	public int getNrmcms() {
		return nrmcms;
	}

	public void setNrmcms(int nrmcms) {
		this.nrmcms = nrmcms;
	}

	public int getNmrgvs() {
		return nmrgvs;
	}

	public void setNmrgvs(int nmrgvs) {
		this.nmrgvs = nmrgvs;
	}

	public int getNrmrgvs() {
		return nrmrgvs;
	}

	public void setNrmrgvs(int nrmrgvs) {
		this.nrmrgvs = nrmrgvs;
	}

	public int getLineComplexity() {
		return lineComplexity;
	}

	public void setLineComplexity(int lineComplexity) {
		this.lineComplexity = lineComplexity;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public int getWr() {
		return wr;
	}
	
	
	
	
	
	

	
	
	
	
}
