package dao;

import dao.SequencePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-24T22:35:57")
@StaticMetamodel(Sequence.class)
public class Sequence_ { 

    public static volatile SingularAttribute<Sequence, Integer> seqNum;
    public static volatile SingularAttribute<Sequence, Integer> patternID;
    public static volatile SingularAttribute<Sequence, Integer> id;
    public static volatile SingularAttribute<Sequence, SequencePK> sequencePK;

}