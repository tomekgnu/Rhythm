package dao;

import dao.EventPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-24T22:35:57")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, EventPK> eventPK;
    public static volatile SingularAttribute<Event, Integer> part;
    public static volatile SingularAttribute<Event, Integer> instrument;
    public static volatile SingularAttribute<Event, Integer> id;
    public static volatile SingularAttribute<Event, Integer> position;
    public static volatile SingularAttribute<Event, Integer> time;

}