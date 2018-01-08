package com.st.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.st.resource.*;
import com.st.resource.StockTradingResourceImpl;

@ApplicationPath("/resource")
public class StockTradingResourceConfig extends Application{
	private Set<Object> singletons;
	private Set<Class<?>> classes;
	
	public StockTradingResourceConfig() {
		classes = Collections.EMPTY_SET;
		singletons = new HashSet<Object>(); 
	}
	
	@Override
	public Set<Object> getSingletons() {
		System.out.println("getSingletons");
		singletons.add(new StockTradingResourceImpl());
		return singletons;
	}
	@Override
	public Set<Class<?>> getClasses() {
		System.out.println("getClasses");
		// TODO Auto-generated method stub
		return super.getClasses();
	}
}
