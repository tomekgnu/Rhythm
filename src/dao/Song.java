/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomek
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Song.findAll", query = "SELECT s FROM Song s")
    , @NamedQuery(name = "Song.findById", query = "SELECT s FROM Song s WHERE s.id = :id")
    , @NamedQuery(name = "Song.findByName", query = "SELECT s FROM Song s WHERE s.name = :name")
    , @NamedQuery(name = "Song.findBySequenceID", query = "SELECT s FROM Song s WHERE s.sequenceID = :sequenceID")})
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "Song")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private String name;
    @Column(name = "Sequence_ID")
    private Integer sequenceID;

    public Song() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequenceID() {
        return sequenceID;
    }

    public void setSequenceID(Integer sequenceID) {
        this.sequenceID = sequenceID;
    }

    @Override
    public int hashCode() {
       return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Song)) {
            return false;
        }
        return this.id.equals(((Integer)object));
    }

    @Override
    public String toString() {
        return "dao.Song[ songPK=" + id + " ]";
    }
    
}
