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
import javax.jws.WebService;

/**
 *
 * @author clementval
 */
@Stateless
@WebService
public class BlogManager implements BlogManagerLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public long insertMessage(String topic, String content) {
        Message msg = new Message();
        msg.setTopic(topic);
        msg.setContent(content);
        em.persist(msg);
        em.flush();
        return msg.getId();
    }

    @Override
    public Message getMessageById(long id) {
        Message msg = (Message)em.find(Message.class, id);
        return msg;
    }
    
    @Override
    public List getAllNotes() {
        return em.createQuery("SELECT n FROM Message n").getResultList();
    }
    
    
    
}
