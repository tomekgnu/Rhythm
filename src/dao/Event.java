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
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")
    , @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id")
    , @NamedQuery(name = "Event.findByPart", query = "SELECT e FROM Event e WHERE e.part = :part")
    , @NamedQuery(name = "Event.findByPosition", query = "SELECT e FROM Event e WHERE e.position = :position")
    , @NamedQuery(name = "Event.findByInstrument", query = "SELECT e FROM Event e WHERE e.instrument = :instrument")
    , @NamedQuery(name = "Event.findByTime", query = "SELECT e FROM Event e WHERE e.time = :time")})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "Event")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private Integer part;
    private Integer position;
    private Integer instrument;
    private Integer time;

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getInstrument() {
        return instrument;
    }

    public void setInstrument(Integer instrument) {
        this.instrument = instrument;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        
        return this.id.equals(((Integer)object));
    }

    @Override
    public String toString() {
        return "dao.Event[ eventPK=" + id + " ]";
    }
    
}
