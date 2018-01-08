package com.ji.resource;

import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path("/texi/{city}")
public class TexiResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enquire(@Context UriInfo uri){
		
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		List<PathSegment> pathSegments = uri.getPathSegments();
		for(PathSegment pathSegment : pathSegments){
			buffer.append(extractedPathSegment(pathSegment));
		}
		
		MultivaluedMap<String,String> queryParameters = uri.getQueryParameters();
		buffer.append("?");
		for(String queryParamKey : queryParameters.keySet()){
			List<String> queryParamValues = queryParameters.get(queryParamKey);
				if(queryParameters.keySet().size()>1){
					for(String queryParamValue : queryParamValues){
						buffer.append(queryParamKey).append("=").append(queryParamValue).append("&");
					}
				}else{
					for(String queryParamValue : queryParamValues){
						buffer.append(queryParamKey).append("=").append(queryParamValue);
					}
				}
		}
		
		return buffer.toString();
		
	}
	
	public String extractedPathSegment(PathSegment pathSegment){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		buffer.append("/").append(pathSegment.getPath());
		MultivaluedMap<String, String> matrixParametersMap = pathSegment.getMatrixParameters();
		for(String paramKey : matrixParametersMap.keySet()){
			List<String> paramValues = matrixParametersMap.get(paramKey);
			for(String paramValue : paramValues){
				buffer.append(";").append(paramKey).append("=").append(paramValue);
			}
		}
		return buffer.toString();
	}
}
