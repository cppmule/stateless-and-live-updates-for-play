/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.springremoting.service;

import com.eif.osf.springremoting.models.Message;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
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
@Stateless
@Transactional
public class BlogManager implements BlogManagerInt {
    private static int counter=0;
    
    @PersistenceContext(name="com.eif.osf_SpringRemoting_war_1.0-SNAPSHOTPU")
    EntityManager em;
    
    
    public long insertMessage(String topic, String content) {
        try {  
            Message msg = new Message();
            msg.setTopic(topic);
            msg.setContent(content);
            
            if(em == null)
                return 20;
            em.merge(msg);
            em.flush();
            return msg.getId();
        } catch (Exception ex) { 
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return counter++;
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
