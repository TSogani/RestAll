package com.re.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.re.resource.DtdcCourierResource;

//@ApplicationPath("/rest/*") will not work
@ApplicationPath("/rest")
public class DtdcCourierApplication extends Application{
	private Set<Class<?>> classes;
	private Set<Object> singletons;
	
	public DtdcCourierApplication() {
		classes = new HashSet<Class<?>>();
		singletons = new HashSet<Object>();
		singletons.add(new DtdcCourierResource());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() {
		
		return singletons;
	}
}
