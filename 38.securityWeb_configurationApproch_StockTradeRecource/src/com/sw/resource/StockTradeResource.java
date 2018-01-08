package com.sw.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/stock")
public class StockTradeResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/price/{stockName}")
	public String getStockPrice(@PathParam("stockName") String stockName) {
		String stockPrice = null;
			
		if (stockName.equals("gold")) {
			stockPrice = "37000/share";
		} else if (stockName.equals("silver")) {
			stockPrice = "32000/share";
		}else{
			stockPrice = "stock not available";
		}
		return stockPrice;
	}
}
