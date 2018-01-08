package com.mbr.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class JAXBMessageBodyWriter implements MessageBodyWriter<Object>{

	@Override
	public long getSize(Object obj, Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType) {
		System.out.println("getSize method");
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType) {
		System.out.println("isWriteable method");
		if(classType.isAnnotationPresent(XmlRootElement.class)){
			System.out.println("isWritable method");
			return true;
		}
		return false;
	}

	@Override
	public void writeTo(Object obj, Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType,
			MultivaluedMap<String, Object> responseHeader, OutputStream os) throws IOException, WebApplicationException {
		System.out.println("from writerTo");
		
		JAXBContext jcontext = null;
		Marshaller marshaller = null;
		
		try {
			jcontext = JAXBContext.newInstance(classType);
			marshaller = jcontext.createMarshaller();
			marshaller.marshal(obj, os);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(e);
		}finally{
			os.close();
		}
	}
}
