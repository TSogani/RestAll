package com.sw.resource;

import java.util.UUID;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.sw.beans.BuyOrder;
import com.sw.beans.Invoice;

@Path("/stock")
@RolesAllowed(value={"admin","trader"})
public class StockTradeResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/price")
	@RolesAllowed(value={"admin"})
	public Invoice getStockPrice(BuyOrder order){
		String status = null;
		Invoice invoice = null;
		
		
		if(order.getCustomerId()==1){
			status = "active";
		}else{
			status = "suspended";
		}
		
		if(status.equals("active")==false){
			throw new NotAuthorizedException(Response.status(Status.FORBIDDEN.getStatusCode()));
		}else{
			invoice = new Invoice();
			invoice.setInvoiceNo(420);
			invoice.setStatus("partial");
		}
		
		return invoice;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getCustomerid")
	@RolesAllowed("trader")
	public String getCustomerId(BuyOrder order){
		
		return String.valueOf(order.getCustomerId());
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/openingtime")
	@PermitAll
	public String getStockMarketOpeningTime(){
		return "10:00 AM";
	}
}
