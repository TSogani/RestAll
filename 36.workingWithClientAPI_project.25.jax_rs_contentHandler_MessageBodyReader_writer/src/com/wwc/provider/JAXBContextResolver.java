package com.wwc.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.wwc.dto.Receipt;
import com.wwc.dto.Subscriber;

@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext>{

	@Override
	public JAXBContext getContext(Class<?> param) {
		JAXBContext jContext = null;
		try {
			jContext = JAXBContext.newInstance(Subscriber.class,Receipt.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			throw new WebApplicationException(e);
			
		}
		return jContext;
	}

}
