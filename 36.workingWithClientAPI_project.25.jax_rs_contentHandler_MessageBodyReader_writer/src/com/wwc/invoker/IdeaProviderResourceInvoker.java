package com.wwc.invoker;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;



import com.wwc.dto.Receipt;
import com.wwc.dto.Subscriber;
import com.wwc.provider.JAXBContextReader;
import com.wwc.provider.JAXBContextResolver;
import com.wwc.provider.JAXBContextWriter;

public class IdeaProviderResourceInvoker extends Invoker{
	private static IdeaProviderResourceInvoker ideaProviderResourceInvoker; 
	private final static String IDEA_PROVIDER_ROOT_URI="http://localhost:8001/25.jax-rs_contentHandler_MessageBodyReader_Writer/rest/idea";
	 
	
	private IdeaProviderResourceInvoker() {
		//no-operation
	}
	
	public static synchronized IdeaProviderResourceInvoker getIdeaProviderResourceInvokerInstance(){
		if(ideaProviderResourceInvoker==null){
			ideaProviderResourceInvoker = new IdeaProviderResourceInvoker();
		}
		return ideaProviderResourceInvoker;
	}
	
	public Receipt doRecharge(Subscriber subscriber) throws JAXBException{
		Receipt receipt = null;
		WebTarget target = null;
		Response response = null;
		
		
		
		target = client.target(IDEA_PROVIDER_ROOT_URI).path("recharge");
		client.register(JAXBContextWriter.class);
		client.register(JAXBContextReader.class);
		client.register(JAXBContextResolver.class);
		
		Invocation invocation = target.request().accept(MediaType.APPLICATION_XML).buildPost(Entity.xml(subscriber));
		response = invocation.invoke();
							//(or)
		/*response = target.request(MediaType.APPLICATION_XML).post(Entity.xml(subscriber));*/
							//(or)
		/*response = target.request().post(Entity.xml(subscriber));*/
		
		if(response.getStatus()==200){
			response.bufferEntity();
			receipt = response.readEntity(Receipt.class);
		}
		
		return receipt;
	}
	
}
