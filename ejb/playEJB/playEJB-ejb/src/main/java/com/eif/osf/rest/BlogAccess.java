/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.rest;


import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import com.eif.osf.services.BlogManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * REST Web Service
 *
 * @author clementval
 */

@Path("service")
@Stateless
public class BlogAccess {
    
    @EJB
    private BlogManager service;
      
    /**
     * Creates a new instance of BlogResource
     */
    public BlogAccess() {

    }

    /**
     * Retrieves representation of an instance of com.eif.osf.rest.BlogResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello, OSF from EJB!!</body></h1></html>";
    }

    /**
     * PUT method for updating or creating an instance of BlogResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
        
    }
    
    
    @Path("insert/{topic}/{content}")
    @GET
    @Produces("text/html")
    public String insertMessage(@PathParam("topic") String topic, @PathParam("content") String content){
        //long id = service.insertMessage(topic, content);
        service.insertMessage(topic, content);
        return "<html lang=\"en\"><body><h1>ID=/"+topic+"/"+content+"</body></h1></html>";
    }

    private static Context getInitialContext() throws NamingException {  
        return new InitialContext();  
    }
}
