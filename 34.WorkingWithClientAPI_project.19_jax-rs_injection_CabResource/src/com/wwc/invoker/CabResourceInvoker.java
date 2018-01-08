package com.wwc.invoker;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

public class CabResourceInvoker extends Invoker {
	private static CabResourceInvoker cabResourceInvoker;
	public final static String CAB_RESOURCE_ROOT_URI = "http://localhost:8001/19.jax-rs_injection/rest/cab-management";

	private CabResourceInvoker() {
		// no-operation
	}

	public synchronized static CabResourceInvoker getCabResourceInvokerInstance() {
		if (cabResourceInvoker == null) {
			cabResourceInvoker = new CabResourceInvoker();
		}
		return cabResourceInvoker;
	}

	public String bookCab(String cabId, String driverId) {
		Response response = null;
		String body = null;

		response = client.target(CAB_RESOURCE_ROOT_URI).path("/trips").request().header("cabId", cabId)
				.cookie(new NewCookie("driverId", driverId)).get();
		if (response.getStatus() == 200) {
			response.bufferEntity();       //If we don't use bufferEntity then we cannot use readEntity() method more then one time becoz InputStream will be closed when we read data only once.That,s why we should always use bufferEntity() method.
			body = response.readEntity(String.class);
			/*body = response.readEntity(String.class);*/
		}
		return body;
	}
	
	public String bookCabForm(String source,String destination,String cabType){
		Response response = null;
		WebTarget target = null; 
		String body = null;
		target = client.target(CAB_RESOURCE_ROOT_URI).path("/bookcab");
		Form form = new Form();
		form.param("source", source);
		form.param("destination", destination);
		form.param("cabType", cabType);
		response = target.request().post(Entity.form(form)); //Entity.form(form) will place the data in key-value pair in request body.
		if(response.getStatus()==200){
			response.bufferEntity();
			body = response.readEntity(String.class);
		}
		return body;
	}
}
