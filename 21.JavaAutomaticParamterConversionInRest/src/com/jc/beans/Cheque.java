package com.jc.beans;

public class Cheque {
	private int chequeNo;
	private String ifscCode;
	
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
	
	@Override
	public String toString() {
		return "Cheque [chequeNo=" + chequeNo + ", ifscCode=" + ifscCode + "]";
	}
	
}
