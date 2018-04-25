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
    @NamedQuery(name = "PatternEvent.findAll", query = "SELECT p FROM PatternEvent p")
    , @NamedQuery(name = "PatternEvent.findById", query = "SELECT p FROM PatternEvent p WHERE p.id = :id")
    , @NamedQuery(name = "PatternEvent.findByPatternID", query = "SELECT p FROM PatternEvent p WHERE p.patternID = :patternID")
    , @NamedQuery(name = "PatternEvent.findByEventID", query = "SELECT p FROM PatternEvent p WHERE p.eventID = :eventID")})
public class PatternEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatternEventPK patternEventPK;
    private Integer id;
    @Column(name = "Pattern_ID")
    private Integer patternID;
    @Column(name = "Event_ID")
    private Integer eventID;

    public PatternEvent() {
    }

    public PatternEvent(PatternEventPK patternEventPK) {
        this.patternEventPK = patternEventPK;
    }

    public PatternEventPK getPatternEventPK() {
        return patternEventPK;
    }

    public void setPatternEventPK(PatternEventPK patternEventPK) {
        this.patternEventPK = patternEventPK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatternID() {
        return patternID;
    }

    public void setPatternID(Integer patternID) {
        this.patternID = patternID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patternEventPK != null ? patternEventPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatternEvent)) {
            return false;
        }
        PatternEvent other = (PatternEvent) object;
        if ((this.patternEventPK == null && other.patternEventPK != null) || (this.patternEventPK != null && !this.patternEventPK.equals(other.patternEventPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.PatternEvent[ patternEventPK=" + patternEventPK + " ]";
    }
    
}
