package com.wwc.provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

@Provider
@Consumes(MediaType.APPLICATION_XML)
public class JAXBContextReader implements MessageBodyReader<Object>{
	
	@Context
	private Providers providers;
	
	@Override
	public boolean isReadable(Class<?> classType, Type rawType, Annotation[] annotation, MediaType mediaType) {
		if(classType.isAnnotationPresent(XmlRootElement.class)){
			return true;
		}
		return false;
	}

	@Override
	public Object readFrom(Class<Object> classType, Type rawType, Annotation[] annotation, MediaType mediaType,
			MultivaluedMap<String, String> responseParam, InputStream is) throws IOException, WebApplicationException {
		JAXBContextResolver jContextResolver = null;
		JAXBContext jContext = null;
		Unmarshaller unmarshaller = null;
		Object obj = null;
		
		jContextResolver = (JAXBContextResolver) providers.getContextResolver(JAXBContext.class, MediaType.APPLICATION_XML_TYPE);
		
		jContext = jContextResolver.getContext(classType);
		
		try {
			 unmarshaller = jContext.createUnmarshaller();
			 obj = unmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}

}
