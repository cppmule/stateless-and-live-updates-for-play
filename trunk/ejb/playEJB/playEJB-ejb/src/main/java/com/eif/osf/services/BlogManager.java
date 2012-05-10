/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.services;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.eif.osf.models.Message;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author clementval
 */


@Path("ejb")
@Stateless
public class BlogManager {
    @PersistenceContext
    EntityManager em;
    
    
    @GET
    @Path("test")
    @Produces("text/html")
    public String test(){
        return "<h1>RESTful EJB<h1>";
    }
    
    
    
    public long insertMessage(String topic, String content) {
        Message msg = new Message();
        msg.setTopic(topic);
        msg.setContent(content);
        em.persist(msg);
        em.flush();
        return msg.getId();
    }

    public Message getMessageById(long id) {
        Message msg = (Message)em.find(Message.class, id);
        return msg;
    }
    
    public List getAllNotes() {
        return em.createQuery("SELECT n FROM Message n").getResultList();
    }
}
