package dao;

import dao.PatternSequence;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-05T15:02:45")
@StaticMetamodel(Song.class)
public class Song_ { 

    public static volatile SingularAttribute<Song, String> name;
    public static volatile SingularAttribute<Song, Long> id;
    public static volatile ListAttribute<Song, PatternSequence> sequenceList;

}