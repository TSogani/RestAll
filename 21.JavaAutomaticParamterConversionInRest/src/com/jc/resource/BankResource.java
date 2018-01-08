package com.jc.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.jc.beans.Cheque;
import com.jc.beans.ChequeOne;
import com.jc.beans.ChequeTwo;

@Path("/bank")
public class BankResource {
	
	//primitive conversion type
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/check-status/{chequeNo}")
	public String getCheckClearence(@PathParam("chequeNo") int chequeNo){
		return "your checkNo : "+chequeNo+" has been cleared";
	}
	
	//array conversion type
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getJaipurCustomerId")
	public String getJaipurCustomerId(@QueryParam("customerId") int[] customerIds){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		for(int customerId : customerIds){
			buffer.append(customerId).append(",");
		}
		return buffer.toString();
	}
	
	//take String value and split it then set into pojo class.	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/fullCheckDetails")
	public String cheque(@QueryParam("cheque") String fullCheckNo){
		String[] tokens = null;
		Cheque cheque = null;
		
		tokens = fullCheckNo.split("-");
		cheque = new Cheque();
		
		cheque.setChequeNo(Integer.parseInt(tokens[0]));
		cheque.setIfscCode(tokens[1]);
		
		return cheque.toString();
	}
	
	//automatic conversion by using valueOf method in pojo class.
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/chequeDetails")
	public String chequeDetails(@QueryParam("cheque") ChequeOne chequeOne){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		buffer.append("chequeNo : ").append(chequeOne.getChequeNo()).append(",").append("ifscCode : ").append(chequeOne.getIfscCode());
		return buffer.toString();
	}
	
	//automatic conversion by using "single String argument constractor"
	@GET
	@Produces
	@Path("/chequeinfo")
	public String getCheckClearenceStatus(@QueryParam("cheque") ChequeTwo chequeTwo){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		buffer.append("chequeNo : ").append(chequeTwo.getChequeNo()).append(",").append("ifscCode : ").append(chequeTwo.getIfscCode()).append(" - cleared");
		
		return buffer.toString();
	}
	
	//automatic type conversion by using List,Set
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/allChequeStatus")
	public String getAllClearenceStatus(@QueryParam("cheque") List<ChequeTwo> chequeList){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		for(ChequeTwo chequeTwo : chequeList){
			buffer.append("chequeNo : ").append(chequeTwo.getChequeNo()).append(",").append("ifscCode : ").append(chequeTwo.getIfscCode()).append(" - cleared").append("     ");
		}
		
		return buffer.toString();
	}
}
