package com.re.application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

import com.je.resource.CourierResource;

public class JourseyCourierApplication extends ResourceConfig{
	public JourseyCourierApplication() {
		packages("com.je.resource");
		
				/*or*/
		
		//register(new CourierResource());
	}
}
