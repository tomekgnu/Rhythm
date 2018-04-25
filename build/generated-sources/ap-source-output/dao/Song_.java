package dao;

import dao.SongPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-24T22:35:57")
@StaticMetamodel(Song.class)
public class Song_ { 

    public static volatile SingularAttribute<Song, String> name;
    public static volatile SingularAttribute<Song, Integer> id;
    public static volatile SingularAttribute<Song, SongPK> songPK;
    public static volatile SingularAttribute<Song, Integer> sequenceID;

}