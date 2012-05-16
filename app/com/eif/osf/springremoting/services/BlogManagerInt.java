package com.eif.osf.springremoting.service;

public interface BlogManagerInt {
    public String getMessage();

    public void setMessage(String mesg);
    public long insertMessage(String topic, String content);
}
