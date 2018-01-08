package com.mbr.common;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mbr.reader.JAXBMessageBodyReader;
import com.mbr.resource.IdeaProvider;
import com.mbr.writer.JAXBMessageBodyWriter;

@ApplicationPath("/rest")
public class ApplicationInitializer extends Application{
	private Set<Object> singletons;
	private Set<Class<?>> classes;
	
	public ApplicationInitializer() {
		singletons = new HashSet<Object>();
		classes = new HashSet<Class<?>>();
		singletons.add(new JAXBMessageBodyReader());
		singletons.add(new JAXBMessageBodyWriter());
	}
	
	@Override
	public Set<Object> getSingletons() {
		
		return singletons;
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		classes.add(IdeaProvider.class);
		return classes;
	}
}
