package dao;

import dao.Pattern;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-12T16:43:26")
@StaticMetamodel(PatternSequence.class)
public class PatternSequence_ { 

    public static volatile ListAttribute<PatternSequence, Pattern> patternList;
    public static volatile SingularAttribute<PatternSequence, Long> id;
    public static volatile SingularAttribute<PatternSequence, Integer> position;

}