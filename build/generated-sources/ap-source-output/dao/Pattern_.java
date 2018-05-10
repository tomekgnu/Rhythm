package dao;

import dao.Event;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T22:08:03")
@StaticMetamodel(Pattern.class)
public class Pattern_ { 

    public static volatile SingularAttribute<Pattern, Integer> duration;
    public static volatile ListAttribute<Pattern, Event> eventList;
    public static volatile SingularAttribute<Pattern, Integer> beats;
    public static volatile SingularAttribute<Pattern, Long> id;
    public static volatile SingularAttribute<Pattern, Integer> position;

}