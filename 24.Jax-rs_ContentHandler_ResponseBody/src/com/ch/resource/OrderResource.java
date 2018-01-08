package com.ch.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/order")
public class OrderResource {
	
	//Response in byte[] to runtime
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/out/bytes")
	public byte[] newOrder(String data){
		return data.getBytes();
	}
	
	//Response in String to runtime
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/out/orderfruit")
	public String newOrderFruit(String fruitOrder){
		return fruitOrder;
	}
	
	//it is not working
	//Response in char[] array to runtime
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/out/orderfurniture")
	public char[] newOrderFurniture(String furnitureOrder){
		return furnitureOrder.toCharArray();
	}
	
	
	//read data from String and send back to runtime as file.
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Path("/new/out/orderfile")
	public File newFileOrder(String data) throws IOException{
		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\DEV\\Desktop\\product.txt"));
			fos.write(data.getBytes());
			fos.close();
		File outFile = new File("C:\\Users\\DEV\\Desktop\\product.txt");
		return outFile;
	}
	
}
