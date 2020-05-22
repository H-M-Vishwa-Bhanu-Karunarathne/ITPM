package controllers;

public class ComplexityLine {
	
	public ComplexityLine() {
		
	}
	
	String line = "";
	int prevCcspps = 0;
	int numberOfConds = 0;
	int weight = 0;
	int lineComplexity = 0;
	int cumCcspps = 0;
	
	public int getLineComplexity() {
		return lineComplexity;
	}
	public void setLineComplexity(int lineComplexity) {
		this.lineComplexity = lineComplexity;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}

	public int getPrevCcspps() {
		return prevCcspps;
	}
	public void setPrevCcspps(int prevCcspps) {
		this.prevCcspps = prevCcspps;
	}

	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getCumCcspps() {
		return cumCcspps;
	}
	public void setCumCcspps(int cumCcspps) {
		this.cumCcspps = cumCcspps;
	}
	public int getNumberOfConds() {
		return numberOfConds;
	}
	public void setNumberOfConds(int numberOfConds) {
		this.numberOfConds = numberOfConds;
	}
	
	public void CalculateLineComplexity(int weight, int NoOfConds, int prevCcspps) {
		this.setLineComplexity((weight * NoOfConds) + prevCcspps);
	}
}
