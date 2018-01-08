package com.wwc.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="subscriber")
@XmlType(propOrder={"mobile","plan","amount"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscriber {
	private String mobile;
	private String plan;
	private String amount;
	
	public String getMobile() {
		return mobile;
	}
	public String getPlan() {
		return plan;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
