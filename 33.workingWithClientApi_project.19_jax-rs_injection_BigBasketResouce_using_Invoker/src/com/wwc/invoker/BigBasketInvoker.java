package com.wwc.invoker;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class BigBasketInvoker extends Invoker{
	public static BigBasketInvoker bigBasketInvoker;
	private final static String BIG_BASKET_ROOT_URI="http://localhost:8001/19.jax-rs_injection/rest/bigbasket";
	public BigBasketInvoker() {
		//no-operation
	}
	
	public static synchronized BigBasketInvoker getInvokerInstance(){
		if(bigBasketInvoker==null){
			bigBasketInvoker = new BigBasketInvoker();
		}
		return bigBasketInvoker;		
	}
	
	public String doSearch(String brand, int brandQuantity, String productName, int productQuantity,String category){
		WebTarget target = null;
		Response response = null;
		String body = null;
		
		target = client.target(BIG_BASKET_ROOT_URI).path("/search").path("{brand}").resolveTemplate("brand", brand)
				.matrixParam("brandQuantity", brandQuantity).path("{productName}").resolveTemplate("productName", productName)
				.matrixParam("productQuantity", productQuantity).queryParam("category", category);
		
		System.out.println(target.getUri().toString());
		
		/*It's Working.
		 * 
		 * Invocation invocation = target.request().build("GET");
		body = invocation.invoke(String.class);*/
		
		response = target.request().get();
		
		if(response.getStatus()==200){
			body = response.readEntity(String.class);
		}
		
		return body;
	}
}
