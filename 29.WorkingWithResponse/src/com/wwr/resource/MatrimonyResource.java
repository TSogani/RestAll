package com.wwr.resource;

import java.net.URISyntaxException;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.wwr.dto.Profile;
import com.wwr.dto.User;

@Path("/matrimony")
public class MatrimonyResource {

	// make a simple response
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/profile/{profileId}")
	public Response getProfile(@PathParam("profileId") String profileId) {

		ResponseBuilder builder = Response.ok();
		Response response = null;
		if (profileId.equals("123")) {
			response = builder.entity("Id is matched").build();
		} else {
			builder.status(Status.BAD_REQUEST).build();     //as part of status we should use Status enum which has provided by jax-rs. We should not use status code. 
			response = builder.entity("No profile found").build();
		}
		return response;
	}

	
	//working with created method and sending the URI
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/new-profile")
	public Response newProfile(User user) throws URISyntaxException {
		Response response = null;
		ResponseBuilder builder = null;

		Profile profile = null;
		profile = new Profile();

		profile.setProfileId(UUID.randomUUID().toString());
		profile.setType("premium");
		profile.setVisits(9090);

		builder = Response.created(new java.net.URI("matrimony/profile/" + profile.getProfileId()));

		response = builder.entity(profile).build();

		return response;
	}

	//sending the URI,COOKIES,HEADER as part of Response
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/new-royal-profile")
	public Response newRoyalProfile(User user) throws URISyntaxException {
		Response response = null;
		ResponseBuilder builder = null;
		NewCookie cookies = null;

		Profile profile = null;
		profile = new Profile();

		profile.setProfileId(UUID.randomUUID().toString());
		profile.setType("premium");
		profile.setVisits(9090);

		cookies = new NewCookie("profileId", profile.getProfileId());

		response = Response.created(new java.net.URI("matrimony/profile/" + profile.getProfileId())).cookie(cookies)
				.header("site", "hindi-matrimony").entity(profile).build();
		
		return response;
	}
}
