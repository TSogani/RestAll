package com.jc.beans;

public class ChequeTwo {
	public int chequeNo;
	public String ifscCode;
	
	
	public ChequeTwo(String fullChequeDetails) {
		String[] tokens = null;
		tokens = fullChequeDetails.split("-");
		
		setChequeNo(Integer.parseInt(tokens[0]));
		setIfscCode(tokens[1]);
	}
	
	public int getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(int chequeNo) {
		this.chequeNo = chequeNo;
	}
	
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
}
