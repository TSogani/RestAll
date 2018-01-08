package com.dd.resource;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.dd.subresource.DomesticCourierSubResource;
import com.dd.subresource.InternationalCourierSubResource;

@Path("/courier")
@Singleton
public class CourierResource {
	
	@Path("/{type}")
	//@Singleton it is not working
	public Object locateService(@PathParam("type") String type){  // it's subresource locator method.
		System.out.println(this.hashCode());
		if(type.equals("domestic")){
			return new DomesticCourierSubResource();
		}else if(type.equals("international")){
			return new InternationalCourierSubResource();
		}
		return null;
	}
}
