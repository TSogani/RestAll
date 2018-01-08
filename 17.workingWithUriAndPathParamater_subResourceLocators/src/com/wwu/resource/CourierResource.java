package com.wwu.resource;

import javax.ws.rs.Path;

import com.wwu.subresource.DomesticCourierSubResource;
import com.wwu.subresource.InternationalCourierSubResource;

@Path("/courier")
public class CourierResource {
	public CourierResource() {
		System.out.println("root resource is created");
	}
	
	@Path("/domestic")
	public DomesticCourierSubResource locateDomesticService(){  //it's called subresource locator method
		return new DomesticCourierSubResource();
	}
	
	@Path("/international")
	public InternationalCourierSubResource locateInternationalCourierService(){ //it's called subresouce locator method
		return new InternationalCourierSubResource();
	}
}
