/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
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
    , @NamedQuery(name = "Pattern.findByBeatTime", query = "SELECT p FROM Pattern p WHERE p.beatTime = :beatTime")})
public class Pattern implements Serializable {

    private static final long serialVersionUID = 1L;
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer beats; 
    private Integer division;
    private Integer beatTime;
    private Integer position;
    
    @OneToMany( targetEntity=Event.class )
    private List eventList;
    
    public Pattern() {
        eventList = new ArrayList();
    }    

    public Pattern(int eventNumber) {
        eventList = new ArrayList();
        for(int i = 0; i < eventNumber; i++)
            eventList.add(i,new MidiEvent());
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
    
    public Integer getBeatTime() {
        return beatTime;
    }

    public void setBeatTime(Integer duration) {
        this.beatTime = duration;
    }
    
    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
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
        try{
           eventList.set(index, evt);        
           
        }catch(IndexOutOfBoundsException ex){
           eventList.add(index,evt); 
        }
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
        return "Pattern";
    }

    
    
    
}
