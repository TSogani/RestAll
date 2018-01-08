package com.je.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/courier")
public class CourierResource {
	@GET
	@Produces(value = "text/plain")
	public String getFrieghtCharges(@QueryParam("source") String source, @QueryParam("destination") String destination,
			@QueryParam("weight") float weight) {
		
		String charges = null;
		if(source!=null && destination!=null && weight>0.0){
			if (source.equals("hyderabad") && destination.equals("jaipur") && weight > 250) {
				charges = "your frieght charge " + source + " to " + destination + " is " + "900";
			} else if (source.equals("hyderabad") && destination.equals("banglore") && weight > 250) {
				charges = "your frieght charge " + source + " to " + destination + " is " + "500";
			}else{
				charges = "Oops! you are checking for wrong value";
			}
			
		}else if(source==null && destination==null && weight==0.0){
			charges = "charges not apply on empty value";
		}
		return charges;
	}
}
