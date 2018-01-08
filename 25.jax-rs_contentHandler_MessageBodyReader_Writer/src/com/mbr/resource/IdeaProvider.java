package com.mbr.resource;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mbr.dto.Receipt;
import com.mbr.dto.Subscriber;

@Path("/idea")
public class IdeaProvider {
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/recharge")
	public Receipt recharge(Subscriber subscriber){
		Receipt receipt = null;
		receipt = new Receipt();
		System.out.println("from recharge");
		receipt.setReceiptNo(String.valueOf(UUID.randomUUID()));
		receipt.setBalance(subscriber.getAmount());
		receipt.setMobile(subscriber.getMobile());
		receipt.setStatus("success");
		
		return receipt;
	}
}
