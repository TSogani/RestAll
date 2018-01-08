package com.ji.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/cab-management")
public class CabResource {
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/accept/{tripNo}")
	public String accept(@PathParam("tripNo") String tripNo,@HeaderParam("cabId") String cabId){
		return "tripNo : "+ tripNo +" cabId : " + cabId;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/trips")
	public String getTrips(@HeaderParam("cabId") String cabId,@CookieParam("driverId") String driverId){
		
		return "cabId : "+ cabId + " driverId : " + driverId;
	}
	
	
	//It will create cookie and send to client.
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/cookies")
	public Response createCookie(){
		return Response.ok().cookie(new NewCookie("driverId", "dri123")).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/dynamictrips")
	public String getTrips(@Context HttpHeaders httpHeaders){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		MultivaluedMap<String, String> requestHeadersMap = httpHeaders.getRequestHeaders();
		for(String headerName : requestHeadersMap.keySet()){
			List<String> headerValues = requestHeadersMap.get(headerName);
			for(String headerValue : headerValues){
				buffer.append(";").append(headerName).append("=").append(headerValue);
			}
		}
		return buffer.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/bookcab")
	public String bookCab(@FormParam("source") String source,@FormParam("destination") String destination,@FormParam("cabType") String cabType){
		
		return "source : " +source+" destination : "+destination+" cabType : "+cabType+" - confirmed";
	}
}
