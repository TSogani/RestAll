package com.ji.resource;

import java.util.List;
import java.util.Set;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;

@Path("/bigbasket")
public class BigBasketResource {
	
	//working with PathSegment
	
	///bigbasket/search/itc;quantity=1;category=gloceries/surfexel;quantity=2?category=offerzone
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/search/{brand}/{productName}")
	public String search(@PathParam("brand") PathSegment brandSegment,
			@PathParam("productName") PathSegment productNameSegment, @QueryParam("category") String category) {
		
		StringBuffer buffer = null;
		
		buffer = new StringBuffer();
		
		buffer.append("/").append(brandSegment.getPath());
		
		MultivaluedMap<String, String> matrixParametersMap = brandSegment.getMatrixParameters();
		for(String paramName : matrixParametersMap.keySet()){
			List<String> paramValues = matrixParametersMap.get(paramName);
			for(String paramValue : paramValues){
				buffer.append(";").append(paramName).append("=").append(paramValue);
			}
		}
		
		buffer.append("/").append(productNameSegment.getPath());
		
		matrixParametersMap = productNameSegment.getMatrixParameters();
		for(String paramKey : matrixParametersMap.keySet()){
			List<String> paramValues = matrixParametersMap.get(paramKey);
			for(String paramValue : paramValues){
				buffer.append(";").append(paramKey).append("=").append(paramValue);
			}
		}
		
		buffer.append("?").append("category").append("=").append(category);
		return buffer.toString();
	}
	
	
	//working with default value with QueryParam
	//note:-QueryParam is optional so if we did't pass any value the it will take default value automaticaly but @DefaultValue is not recommended to use.
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/care")
	public String getCoustomerCareContect(@DefaultValue("mumbai") @QueryParam("city") String city){
		return "city : "+ city +" 1800-202-888";
	}
}









