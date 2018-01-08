package com.ch.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="receipt")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"receiptNo","mobile","balance","status"})
public class Receipt {
	private String receiptNo;
	private String mobile;
	private String balance;
	private String status;
	
	public String getReceiptNo() {
		return receiptNo;
	}
	public String getMobile() {
		return mobile;
	}
	
	public String getBalance() {
		return balance;
	}
	public String getStatus() {
		return status;
	}
	
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
