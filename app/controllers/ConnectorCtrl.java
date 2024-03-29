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
import java.net.URI;
import org.w3c.dom.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eif.osf.springremoting.service.BlogManagerInt;




public class ConnectorCtrl extends Controller {

	private static final String pageTitle = "Connector EJB Demo";
	private static Form<Article> articleForm = form(Article.class);
	
	
	public static Result callWithSOAP() {
		
		return ok();		
	}
	
	
	public static Result ejbform(){
		return ok(ejbconnectorform.render("REST", articleForm));
	}
	
	
	public static Result callWithRESTForm() {
		Form<Article> form = form(Article.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(ejbconnectorform.render("REST", form));
		}

		String topic = form.get().topic;
		String content = form.get().content;
		
		String output = "NULL";
		try {
 
			Client client = Client.create();
	 		URI uristr = new URI("http://localhost:8080/RESTfulEJB/manager/myresource/insert/"+topic+"/"+content);
	 
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
	  return ok(ejbconnector.render(output));
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
	  return ok(ejbconnector.render(output));
	}
	
	public static Result callWithRMIForm() {
		Form<Article> form = form(Article.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(ejbconnectorform.render("RMI", form));
		}

		String topic = form.get().topic;
		String content = form.get().content;
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("rmi-client-context.xml");
 		BlogManagerInt blog = (BlogManagerInt) ctx.getBean("blogmanager");
  		long id = blog.insertMessage(topic, content);
  		String output = id+"";
		return ok(ejbconnector.render(output));
	}
	
	
	public static Result callWithSpringRemote() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("rmi-client-context.xml");
 		BlogManagerInt blog = (BlogManagerInt) ctx.getBean("blogmanager");
  		long id = blog.insertMessage("test", "content");
  		String output = id+"";
		return ok(ejbconnector.render(output));
	}
}
