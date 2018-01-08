package com.wwe.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wwe.exception.CardAlreadyIssuedException;

@Provider
public class CardAlreadyIssuedExceptionMapper implements ExceptionMapper<CardAlreadyIssuedException> {

	@Override
	public Response toResponse(CardAlreadyIssuedException paramExeption) {
		Response response = null;
		response = Response.serverError()
				.entity("<error><error-code>098</error-code><error-message>Card already issued</error-message></error>")
				.build();
		return response;
	}

}
