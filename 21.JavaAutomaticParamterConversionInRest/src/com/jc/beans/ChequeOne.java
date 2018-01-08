package com.jc.beans;

public class ChequeOne {
	private int chequeNo;
	private String ifscCode;
	
	public static ChequeOne valueOf(String fullChequeNo){
		String[] tokens = null;
		tokens = fullChequeNo.split("-");
		ChequeOne chequeOne = new ChequeOne();
		chequeOne.setChequeNo(Integer.parseInt(tokens[0]));
		chequeOne.setIfscCode(tokens[1]);
		
		return chequeOne;
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
