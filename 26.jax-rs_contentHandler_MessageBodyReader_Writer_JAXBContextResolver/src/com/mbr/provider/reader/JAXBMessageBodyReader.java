package com.mbr.provider.reader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

@Provider
@Consumes(MediaType.APPLICATION_XML)
public class JAXBMessageBodyReader implements MessageBodyReader<Object>{
	
	public JAXBMessageBodyReader() {
		System.out.println("JAXBMessageBodyReader hashcode : "+this.hashCode());
	}
	@Context
	private Providers providers;
	
	@Override
	public boolean isReadable(Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType) {
		System.out.println("isReadable method");
		if(classType.isAnnotationPresent(XmlRootElement.class)){
			return true;
		}
		
		return false;
	}

	@Override
	public Object readFrom(Class<Object> classType, Type rawType, Annotation[] annotation, MediaType mediaType,
			MultivaluedMap<String, String> requestParams, InputStream is) throws IOException, WebApplicationException {
		System.out.println("readFrom method");
		Object obj = null;
		JAXBContext jcontext = null;
		Unmarshaller unmarshaller = null;
		ContextResolver<JAXBContext> contextResolver = null;
		
		try {
			contextResolver = providers.getContextResolver(JAXBContext.class, MediaType.APPLICATION_XML_TYPE);
			jcontext = contextResolver.getContext(classType);
			unmarshaller = jcontext.createUnmarshaller();
			obj = unmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
		return obj;
	}
}
