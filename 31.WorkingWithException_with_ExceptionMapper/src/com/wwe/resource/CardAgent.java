package com.wwe.resource;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wwe.exception.CardAlreadyIssuedException;

@Path("/card")
public class CardAgent {
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/apply/{bank}/{uuid}")
	public Response applyCardWithBetterExceptionManagementOne(InputStream is,@PathParam("bank") String bank,@PathParam("uuid") String uuid) throws CardAlreadyIssuedException{
		
		Response response = null;
		
			if(bank.equals("icici") || uuid.equals("1")){
				throw new CardAlreadyIssuedException("Already card has been issued");
			}else{
				response = Response.ok().entity("Apply card application has been processed sucessfully").build();
			}
		
		return response;
	}
}
