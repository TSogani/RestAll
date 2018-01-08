package com.cg.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="status")
@XmlType(propOrder={"awbno","type","status"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
	private String awbno;
	private String type;
	private String status;
	
	public String getAwbno() {
		return awbno;
	}
	public void setAwbno(String awbno) {
		this.awbno = awbno;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
