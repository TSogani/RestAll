package com.ji.resource;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ji.beans.Match;

@Path("/crick/{tournament}")
public class CricketInfoResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/score/{teamOne}/{teamTwo}")
	public String getScore(@BeanParam Match match){
		return match.toString();
	}
}
