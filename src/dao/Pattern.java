/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import misc.MidiEvent;

/**
 *
 * @author Tomek
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pattern.findAll", query = "SELECT p FROM Pattern p")
    , @NamedQuery(name = "Pattern.findById", query = "SELECT p FROM Pattern p WHERE p.id = :id")
    , @NamedQuery(name = "Pattern.findByBeats", query = "SELECT p FROM Pattern p WHERE p.beats = :beats")
    , @NamedQuery(name = "Pattern.findByDuration", query = "SELECT p FROM Pattern p WHERE p.duration = :duration")})
public class Pattern implements Serializable {

    private static final long serialVersionUID = 1L;
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer beats;    
    private Integer duration;
    private Integer position;
    
    @OneToMany( targetEntity=Event.class )
    private List eventList;
    
    public Pattern() {
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBeats() {
        return beats;
    }

       
    public void setPosition(Integer num){
        this.position = num;
    }
    
    public Integer getPosition(){
        return this.position;
    }
    
    public void setBeats(Integer beats) {
        this.beats = beats;
    }
    
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public List getEventList(){
        return this.eventList;
    }
    
    public void setEventList(List list){
        this.eventList = list;
    }
    
    public MidiEvent getEventAt(int index){
        if(eventList.size() > 0)
            return (MidiEvent)eventList.get(index);
        return null;
    }
    
    public void addEvent(int index,MidiEvent evt){
        if(eventList.get(index) != null)
            eventList.set(index, evt);
        else
            eventList.add(index,evt);
    }
    
    public boolean hasEvents() {
        return eventList.size() > 0;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pattern)) {
            return false;
        }
        
        return this.id.equals(((Integer)object));
    }

    @Override
    public String toString() {
        return "dao.Pattern[ patternPK=" + id + " ]";
    }

    
    
    
}
