package com.wwc.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class JAXBContextWriter implements MessageBodyWriter<Object>{
	
	@Context
	private Providers providers;
	
	@Override
	public long getSize(Object object, Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType) {
		if(classType.isAnnotationPresent(XmlRootElement.class)){
			return true;
		}
		return false;
	}

	@Override
	public void writeTo(Object obj, Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType,
			MultivaluedMap<String, Object> requestParams, OutputStream os) throws IOException, WebApplicationException {
		JAXBContextResolver jContextResolver = null;
		JAXBContext jContext = null;
		Marshaller marshaller = null;
		
		System.out.println("hello");
		jContextResolver = (JAXBContextResolver) providers.getContextResolver(JAXBContext.class, MediaType.APPLICATION_XML_TYPE);
		jContext = jContextResolver.getContext(classType);
		try {
			marshaller = jContext.createMarshaller();
			marshaller.marshal(obj, os);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			throw new WebApplicationException(e);
		}
		
	}
	
}
