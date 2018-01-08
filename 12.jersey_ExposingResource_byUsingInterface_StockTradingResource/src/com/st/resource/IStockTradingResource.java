package com.st.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/stocktrade")
public interface IStockTradingResource {
	@GET
	@Produces("text/plain")
	public float getStock(@QueryParam("stockName")String stockName,@QueryParam("market")String market);
}
