package controllers;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import models.*;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.*;
import play.mvc.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.*;


public class ConnectorCtrl extends Controller {

	private static final String pageTitle = "Connector EJB Demo";

	public static Result callWithSOAP() {
		return ok();		
	}
	
	public static Result callWithREST() {
		String output = "NULL";
		try {
 
			Client client = Client.create();
	 
			WebResource webResource = client
			   .resource("http://localhost:8080/RESTfulEJB/manager/myresource/insert/test/ici");
	 
			ClientResponse response = webResource.accept("text/plain")
	                   .get(ClientResponse.class);
	 
			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
	 
			output = response.getEntity(String.class);
	 
			System.out.println("Output from Server .... \n");
			System.out.println(output);
 
	  } catch (Exception e) {
 
			e.printStackTrace();
 
	  }
	  return ok(index.render(output));
	}
	
	public static Result callWithRMI() {
		return ok();
	}
}
