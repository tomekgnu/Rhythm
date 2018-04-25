/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @EmbeddedId
    protected SongPK songPK;
    private Integer id;
    private String name;
    @Column(name = "Sequence_ID")
    private Integer sequenceID;

    public Song() {
    }

    public Song(SongPK songPK) {
        this.songPK = songPK;
    }

    public SongPK getSongPK() {
        return songPK;
    }

    public void setSongPK(SongPK songPK) {
        this.songPK = songPK;
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
        int hash = 0;
        hash += (songPK != null ? songPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Song)) {
            return false;
        }
        Song other = (Song) object;
        if ((this.songPK == null && other.songPK != null) || (this.songPK != null && !this.songPK.equals(other.songPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Song[ songPK=" + songPK + " ]";
    }
    
}
