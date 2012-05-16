/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.springremoting.service;

import com.eif.osf.springremoting.models.MessageBis;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author clementval
 */
public class BlogManager implements BlogManagerInt {
    
    @PersistenceContext
    EntityManager em;
    
    
    public long insertMessage(String topic, String content) {
        try {
            em.getTransaction().begin();
            MessageBis msg = new MessageBis();
            msg.setTopic(topic);
            msg.setContent(content);
            
            if(em == null)
                return 20;
            em.persist(msg);
            em.flush();
            em.getTransaction().commit();
            return msg.getId();
        } catch (Exception ex) { 
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    
    
    private String mesg = "Spring is simple";

    public String getMessage() {
       // long id = insertMessage("test", "content");
        return mesg;
    }

    public void setMessage(String mesg) {
        this.mesg = mesg;
    }
}
