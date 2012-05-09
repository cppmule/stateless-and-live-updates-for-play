/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.services;

import com.eif.osf.models.Message;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author clementval
 */
@Local
public interface BlogManagerLocal {
    public long insertMessage(String topic, String content);
    public Message getMessageById(long id);
    public List getAllNotes();
}
