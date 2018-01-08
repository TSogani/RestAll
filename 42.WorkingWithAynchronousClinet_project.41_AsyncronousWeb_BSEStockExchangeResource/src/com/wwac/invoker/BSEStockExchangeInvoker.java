package com.wwac.invoker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.core.Response;

public class BSEStockExchangeInvoker extends Invoker{
	private static final String BSE_STOCK_EXCHANGE_ROOT_URI="http://localhost:9000/41.AsyncronousWeb_BSEStockExchangeResource/rest/stock";
	private static BSEStockExchangeInvoker bseStockExchangeInvoker;
	
	public BSEStockExchangeInvoker() {
		//no-operation
	}
	
	public static synchronized BSEStockExchangeInvoker getBSEStockExchangeInvokerInstance(){
		if(bseStockExchangeInvoker==null){
			bseStockExchangeInvoker = new BSEStockExchangeInvoker();
		}
		return bseStockExchangeInvoker;
	}
	
	public String getStockPrice(String stockName,String city) throws InterruptedException, ExecutionException{
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		//find the stock price
		Future<Response> future = client.target(BSE_STOCK_EXCHANGE_ROOT_URI).path("price").path("{stockName}").resolveTemplate("stockName", stockName).request().async().get();
		Response response = future.get();
		if(response.getStatus()==200){
			
			String price = response.readEntity(String.class);
			buffer.append("FirstTime : ").append("price : ").append(price);
			
		}
		//again send the request for finding the stock price
		//we can send any no of request to the same resource then it will not behaive like synchrouns client.
		//every Response object will wait in aynchrous manner in Future object.So client will not wait on every request and response.
		future = client.target(BSE_STOCK_EXCHANGE_ROOT_URI).path("price").path("{stockName}").resolveTemplate("stockName", stockName).request().async().get();
		response = future.get();
		if(response.getStatus()==200){
			
			String price = response.readEntity(String.class);
			buffer.append("   SecondTime : ").append("price : ").append(price);
			
		}
		
		
		//for finding the agent name based on city.
		Future<Response> future1 = client.target(BSE_STOCK_EXCHANGE_ROOT_URI).path("findagentName").path("{city}").resolveTemplate("city", city).request().async().get();
		Response response1 = future1.get();
		if(response.getStatus()==200){
			String agentName = response1.readEntity(String.class);
			buffer.append("    AgentName : ").append(agentName);
		}
		
		return buffer.toString();
	}
}
