package com.wwc.invoker;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

abstract public class Invoker {
	protected Client client;
	
	public void open(){
		if(client  == null){
			client = ClientBuilder.newClient();
		}
	}
	
	public void close(){
		if(client!=null){
			client.close();
		}
	}
}
