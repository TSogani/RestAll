package com.cpc.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cpc.convertor.ChequeParamConvertor;
import com.cpc.dto.Cheque;

@Path("/ibank")
public class IBankResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/cheque/{chequeNo}/status")
	public String getCheckStatus(@PathParam("chequeNo") Cheque cheque){
		System.out.println("getCheckStatus");
		return "chequeNo : "+cheque.getChequeNo()+","+" ifscCode : "+cheque.getIfscCode()+" -cleared";
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getCheckDetails")
	public Cheque getCheckDetails(){
		Cheque cheque = new Cheque();
		cheque.setIfscCode("ifsc123");
		cheque.setChequeNo(123);
		return cheque;
	}
}
