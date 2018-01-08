package com.cpc.dto;

import java.io.Serializable;

public class Cheque implements Serializable{
	private String ifscCode;
	private int chequeNo;
	
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	
	public int getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(int chequeNo) {
		this.chequeNo = chequeNo;
	}
}
