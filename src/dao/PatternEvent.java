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
    @NamedQuery(name = "PatternEvent.findAll", query = "SELECT p FROM PatternEvent p")
    , @NamedQuery(name = "PatternEvent.findById", query = "SELECT p FROM PatternEvent p WHERE p.id = :id")
    , @NamedQuery(name = "PatternEvent.findByPatternID", query = "SELECT p FROM PatternEvent p WHERE p.patternID = :patternID")
    , @NamedQuery(name = "PatternEvent.findByEventID", query = "SELECT p FROM PatternEvent p WHERE p.eventID = :eventID")})
public class PatternEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "PatternEvent")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "Pattern_ID")
    private Integer patternID;
    @Column(name = "Event_ID")
    private Integer eventID;

    public PatternEvent() {
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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatternEvent)) {
            return false;
        }
        return this.id.equals(((Integer)object));
    }

    @Override
    public String toString() {
        return "dao.PatternEvent[ patternEventPK=" + id + " ]";
    }
    
}
