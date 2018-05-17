/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomek
 */

public class Pattern  {
    
    private Integer beats; 
    private Integer division;
    private Integer beatTime;
    private Integer position;   
    private List eventList;
    
    public Pattern() {
        eventList = new ArrayList();
    }    

    public Pattern(int eventNumber) {
        eventList = new ArrayList();
        for(int i = 0; i < eventNumber; i++)
            eventList.add(i,new MidiEvent());
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
        
    
    
}
