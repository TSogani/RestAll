package com.mbr.provider.jaxbcontextResolver;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.mbr.dto.Receipt;
import com.mbr.dto.Subscriber;

@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext>{
	
	public JAXBContextResolver() {
		System.out.println("JAXBMessageBodyResolver hashcode : "+this.hashCode());
	}
	@Override
	public JAXBContext getContext(Class<?> classType) {
		JAXBContext jContext = null;
		try {
			jContext = JAXBContext.newInstance(Subscriber.class,Receipt.class);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
		return jContext;
	}

}
