package com.oww.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.oww.resource.OxygenEcashAgent;

@ApplicationPath("/resource")
public class OxygenResourceConfig extends Application{
	
	
	private Set<Object> singletons;
	private Set<Class<?>> classes;
	
	public OxygenResourceConfig() {
		classes = Collections.EMPTY_SET;
		singletons = new HashSet<Object>();
		singletons.add(new OxygenEcashAgent());
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
