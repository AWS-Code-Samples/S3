package com.infotree.qliktest;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class AmazonS3Client {
	
	public static void main(String[] args) {
		
		String url="http://localhost:8080/AmazonS3RestService/getVideo";
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		String requestUrl=url+"?tenantName=infotree&userName=satya&uniqueId=1";
		System.out.println("url: ->"+requestUrl);
	    WebResource service = client.resource(requestUrl);
	    WebResource.Builder builder=service.accept(MediaType.TEXT_PLAIN);
	    System.out.println(builder.get(String.class));  
	    
	}
}
