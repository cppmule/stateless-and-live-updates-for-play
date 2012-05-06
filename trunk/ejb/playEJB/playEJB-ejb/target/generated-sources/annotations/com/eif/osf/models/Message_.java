package com.eif.osf.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-06T13:20:28")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, Long> id;
    public static volatile SingularAttribute<Message, String> topic;
    public static volatile SingularAttribute<Message, String> content;

}