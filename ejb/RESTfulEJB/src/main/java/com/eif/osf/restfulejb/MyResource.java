/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.restfulejb;

import com.eif.osf.restfulejb.models.Message;
import java.net.URL;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
@Stateless
public class MyResource {
    @PersistenceContext
    EntityManager em;
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {

			

        return "Hi there!";
    }
    
    
    @GET
    @Path("insert/{topic}/{content}")
    @Produces("text/plain")
    public String restInsertMessage(@PathParam("topic") String topic, @PathParam("content") String content) {
        long id = insertMessage(topic, content);
        return "Inserted"+id;
    }
    
    
    public long insertMessage(String topic, String content) {
        Message msg = new Message();
        msg.setTopic(topic);
        msg.setContent(content);
        em.persist(msg);
        em.flush();
        return msg.getId();
    }
    
    
}