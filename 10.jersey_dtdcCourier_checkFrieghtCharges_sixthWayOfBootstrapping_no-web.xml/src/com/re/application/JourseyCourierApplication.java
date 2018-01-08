package com.re.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class JourseyCourierApplication extends ResourceConfig{
	public JourseyCourierApplication() {
		packages("com.je.resource");
		
				/*or*/
		
		//register(new CourierResource());
	}
}
