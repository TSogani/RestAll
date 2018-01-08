package com.wwu.resource;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/icici-netbanking/{accountNo}")
public class IConnectResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public float getAccountBalance(@PathParam("accountNo") String accountNo){
		return 99.00f;
	}
}
