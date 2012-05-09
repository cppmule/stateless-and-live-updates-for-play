/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eif.osf.services;

import com.eif.osf.models.Message;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author clementval
 */
@Remote
public interface BlogManagerRemote {
    public long insertMessage(String topic, String content);
    public Message getMessageById(long id);
    public List getAllNotes();
}
