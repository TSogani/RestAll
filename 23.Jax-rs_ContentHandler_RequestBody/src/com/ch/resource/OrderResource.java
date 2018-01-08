package com.ch.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.StreamingOutput;

@Path("/order")
public class OrderResource {
	
	//Charcter data read and send output
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/in")
	public StreamingOutput newOrder(InputStream is) throws IOException {
		int c = -1;
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		while((c = is.read()) != -1){
			buffer.append((char)c);
		}
		return new DataStreamingOutput(buffer.toString());
	}
	
	//read data from Reader(It's also use for localization)
		@POST
		@Consumes(MediaType.TEXT_PLAIN)
		@Produces(MediaType.TEXT_PLAIN)
		@Path("/new/reader")
		public StreamingOutput newOrder(Reader reader) throws IOException{
			StringBuffer buffer = null;
			char[] buf = null;
			
			buffer = new StringBuffer();
			buf = new char[256];
			
			while((reader.read(buf)) != -1){
				buffer.append(buf);
			}
			
			return new DataStreamingOutput(buffer.toString());
		}
		
	
	
	
	
	//read data from byte[] and send back to client as same
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/bytes")
	public StreamingOutput newOrder(byte[] raw){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		for(byte c : raw){
			buffer.append((char)c);
		}
		
		return new DataStreamingOutput(buffer.toString());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/form")
	public StreamingOutput newOrder(MultivaluedMap<String, String> requestParams){
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		
		for(String paramKey : requestParams.keySet()){
			buffer.append(paramKey+" : ");
			List<String> paramValues = requestParams.get(paramKey);
			
			for(String paramValue : paramValues){
				buffer.append(paramValue).append(",");
			}
		}
		
		
		return new DataStreamingOutput(buffer.toString());
	}
	
	//read data from string
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/string")
	public StreamingOutput newOrder(String order){
		
		return new DataStreamingOutput(order);
	}
	
	//its not working check it......
	//read data from char[] array
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/chars")
	public StreamingOutput newOrders(char[] order) {
		return new DataStreamingOutput(new String(order));
	}
	
	//read data from file and file mediatype is multipart bcoz we can send any file and along with we can send xml and any data as part of respose body.
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/new/file")
	public StreamingOutput newBulkOrder(File file) throws IOException{
		StringBuffer buffer = null;
		buffer = new StringBuffer();
		int c = -1;
		FileInputStream fis = new FileInputStream(file);
		while((c = fis.read()) != -1){
			buffer.append((char)c);
		}
		return new DataStreamingOutput(buffer.toString());
	}
	
	//inner class
	private class DataStreamingOutput implements StreamingOutput{
		private String data;
		
		public DataStreamingOutput(String data) {
			this.data = data;
		}
		
		@Override
		public void write(OutputStream out) throws IOException, WebApplicationException {
			/* working..
			 * 
			 * PrintWriter writer = null;     
			writer = new PrintWriter(out);
			writer.println(data);
			writer.close();*/
			
			out.write(data.getBytes());
			out.close();
		}
	} 
}
