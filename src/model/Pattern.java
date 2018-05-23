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

public class Pattern  implements Cloneable{
    private Integer ID;
    private String name;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private Integer beats; 
    private Integer division;
    private Integer beatTime;
    private Integer repeat;   
    private List eventList;
    
    public Pattern() {
        eventList = new ArrayList();
        ID = 0;
        repeat = 1;
    }    

    public Pattern(Pattern p){
        eventList = p.eventList;
        this.ID = p.ID;
        this.beatTime = p.beatTime;
        this.beats = p.beats;
        this.division = p.division;
        
    }
    
    public Pattern(int eventNumber) {
        eventList = new ArrayList();
        ID = 0;
        for(int i = 0; i < eventNumber; i++)
            eventList.add(i,new MidiEvent());
        
        repeat = 1;
    }

    public Integer getBeats() {
        return beats;
    }

       
    public void setRepeat(Integer num){
        this.repeat = num;
    }
    
    public Integer getRepeat(){
        return this.repeat;
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
    public String toString(){
        return "Pattern " + this.ID;
    }
    
   
    public Pattern copy()  {
        return new Pattern(this);
    }
}
