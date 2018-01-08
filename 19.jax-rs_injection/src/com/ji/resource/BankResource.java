package com.ji.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ji.beans.Cheque;

@Path("/bank")
public class BankResource {
	
	
	//integer injection
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/balance/{accno : \\d+}/{type : .*}")
	public String getBalance(@PathParam("accno") int accno,@PathParam("type")String type){
		return "your accno : "+accno+" from "+ type+" have 222 ruppees";
	}
	
}
