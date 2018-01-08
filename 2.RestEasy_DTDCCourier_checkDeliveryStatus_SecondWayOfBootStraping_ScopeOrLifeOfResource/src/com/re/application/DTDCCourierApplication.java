package com.re.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.re.resource.DTDCCourierResource;

public class DTDCCourierApplication extends Application{
	private Set<Class<?>> classes;
	private Set<Object> singletons;
	
	public DTDCCourierApplication() {
		classes = new HashSet<Class<?>>();
		singletons = new HashSet<Object>();
		singletons.add(new DTDCCourierResource());
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
