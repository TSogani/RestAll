package com.ch.resource;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ch.dto.Receipt;
import com.ch.dto.Subscriber;

@Path("/idea")
public class IdeaProvider {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	/*@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)*/
	@Path("/recharge")
	public Receipt recharge(Subscriber subscriber){
		Receipt receipt = null;
		receipt = new Receipt();
		
		receipt.setReceiptNo(UUID.randomUUID().toString());
		receipt.setBalance(subscriber.getAmount());
		receipt.setMobile(subscriber.getMobile());
		receipt.setStatus("success");
		
		return receipt;
	}
}
