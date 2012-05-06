/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.services;

import javax.ejb.Local;
import com.eif.osf.models.Message;
import java.util.List;

/**
 *
 * @author clementval
 */
@Local
public interface BlogManagerLocal {

    long insertMessage(String topic, String content);

    Message getMessageById(long id);
    
    List getAllNotes();
    
}
