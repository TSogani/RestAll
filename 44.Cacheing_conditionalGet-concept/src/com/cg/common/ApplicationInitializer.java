package com.cg.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.cg.resource.CourierResource;

@ApplicationPath("/rest")
public class ApplicationInitializer extends Application{
	private Set<Object> singletons;
	private Set<Class<?>> classes;
	
	public ApplicationInitializer() {
		classes = Collections.emptySet();
		singletons = new HashSet<Object>();
		singletons.add(new CourierResource());
	}
	
	@Override
	public Set<Object> getSingletons() {
		// TODO Auto-generated method stub
		return singletons;
	}
	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		return super.getClasses();
	}
}
