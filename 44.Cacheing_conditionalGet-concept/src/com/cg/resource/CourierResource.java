package com.cg.resource;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.cg.beans.Courier;
import com.cg.beans.Status;

@Path("/courier")
public class CourierResource {
	
	private Map<String, Status> courierStatusMap;
	
	public CourierResource() {
		System.out.println("object created");
		courierStatusMap = new ConcurrentHashMap<String,Status>();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/book-parsel")
	public Response bookParsel(Courier courier){
		Status status = null;
		Response response = null;
		String awbno = null;
		
		awbno = UUID.randomUUID().toString();
		status = new Status();
		status.setAwbno(awbno);
		status.setType("Express");
		status.setStatus("Accepted");
		
		courierStatusMap.put(awbno, status);
		
		response = Response.ok().entity(awbno).build();
		return response;
	}
	
	@GET
	@Path("/status/{awbno}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCourierStatus(@PathParam("awbno") String awbno,@Context Request request){
		CacheControl cacheControl = null;
		Status status = null;
		Response response = null;
		int etag = 0;
		
		if(courierStatusMap.containsKey(awbno)==false){
			throw new WebApplicationException(javax.ws.rs.core.Response.Status.BAD_REQUEST);
		}
		cacheControl = new CacheControl();
		cacheControl.setMaxAge(2000);
		cacheControl.setMustRevalidate(true);
		status = courierStatusMap.get(awbno);
		etag = status.hashCode();             //server-side etag
		ResponseBuilder responseBuilder = request.evaluatePreconditions(new EntityTag(String.valueOf(etag)));//if Etag is not match then ResponseBuilder object will be created.
		//if etag value are not matched then ResponseBuilder object will be created.
		if(responseBuilder!=null){    //no-change
			response = responseBuilder.cacheControl(cacheControl).build();
			return response;
		}
		response  = Response.ok().cacheControl(cacheControl).tag(new EntityTag(String.valueOf(etag))).entity(status).build();
				
		return response;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/update")
	public Response update(Status status){
		Response response = null;
		String awbNo = null;
				
		awbNo = status.getAwbno();
		
		courierStatusMap.put(awbNo, status);
		
		response = Response.ok().entity("updated").build();
		
		return response;
	}
}	