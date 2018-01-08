package com.re.application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.je.resource.CourierResource;

public class JourseyCourierApplication extends Application{
	private Set<Object> singletons;
	private Set<Class<?>> classes;
	
	public JourseyCourierApplication() {
		classes = Collections.EMPTY_SET;
		singletons = new HashSet<Object>();
		singletons.add(new CourierResource());
	}
	
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
}
