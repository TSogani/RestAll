package com.wwc.client.dtdc;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class DTDCCoriourClient {
	public static void main(String[] args) {
		ClientBuilder clientBuilder = null;
		Client client = null;
		
		clientBuilder = ClientBuilder.newBuilder();
		clientBuilder.property("connection.timeout", 1000);
		
		client = clientBuilder.build();
		
		client.property("connection.timeout", 1000);
		WebTarget dtdcTarget = client.target("http://localhost:8001/10.jersey_dtdcCourier_checkFrieghtCharges_sixthWayOfBootstrapping_no-web.xml/rest/courier");
		dtdcTarget = dtdcTarget.queryParam("source", "hyderabad");
		dtdcTarget = dtdcTarget.queryParam("destination", "jaipur");
		dtdcTarget = dtdcTarget.queryParam("weight", "400");
		Response response = dtdcTarget.request().get();
		if(response.getStatus()==200){
			String body = response.readEntity(String.class);
			System.out.println(body);
			
		}
		
		
		
	
		
	}
}
