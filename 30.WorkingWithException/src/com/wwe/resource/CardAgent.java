package com.wwe.resource;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.wwe.exception.CardAlreadyIssuedException;

@Path("/card")
public class CardAgent {

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/apply/{bank}/{uuid}")
	public Response applyCard(InputStream is, @PathParam("bank") String bank, @PathParam("uuid") String uuid)
			throws CardAlreadyIssuedException {
		Response response = null;
		ResponseBuilder builder = null;
		builder = Response.ok();

		if (bank.equals("icici") || uuid.equals("1")) {
			throw new CardAlreadyIssuedException("Card already has been issued to user");
		}

		response = builder.entity("Card issue process successfully").build();

		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/apply-card/{bank}/{uuid}")
	public Response applyCardWithBetterExceptionManagement(InputStream is, @PathParam("bank") String bank,
			@PathParam("uuid") String uuid) {
		Response response = null;

		// service
		try {
			if (bank.equals("icici") || uuid.equals("2")) {
				throw new CardAlreadyIssuedException("Already already has been issued.");
			} else {
				response = Response.ok().entity("Card will issue after two days").build();
			}

		} catch (CardAlreadyIssuedException caie) {
			response = Response.serverError()
					.entity("<error><error-code>091</error-code><error-message>Already card has been issued</error-message></error>")
					.build();
		}
		return response;
	}
}
