package com.wwr.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="profile")
@XmlType(propOrder={"profileId","type","visits"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Profile {
	private String profileId;
	private String type;
	@XmlElement(type=Integer.class)
	private int visits;
	
	public String getProfileId() {
		return profileId;
	}
	public String getType() {
		return type;
	}
	
	public int getVisits() {
		return visits;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
}
