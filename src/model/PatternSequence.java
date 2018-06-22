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

public class PatternSequence {

    private Integer numOfBytes;
    private Integer numOfPats;
    private List patternList;    
    private Integer maxResolution;
    
    public Integer getNumOfPats() {
        return this.numOfPats;
    }

    public Integer getNumOfBytes() {
        return numOfBytes;
    }
    
    
    public PatternSequence(){
        this.patternList = new ArrayList();
        numOfBytes = 0;
        maxResolution = 0;
        numOfPats = 0;
    }
        
    public List getPatternList(){
        return this.patternList;
    }

    public void setPatternList(List list){
        this.patternList = list;
    }
        
    public Integer getMaxResolution(){
        return this.maxResolution;
    }
    
    public Pattern getPatternAt(int index){
        return (Pattern)patternList.get(index);        
    }
    
    public void addPattern(int index,Pattern pat){
        int currentEventNumber = pat.getEventList().size();
        int currentRepeat = pat.getRepeat();
        int currentResolution = pat.getBeats() * pat.getDivision();
        if(currentResolution > maxResolution)
            this.maxResolution = currentResolution;
        this.numOfBytes += (currentRepeat * (currentEventNumber + 16));
        this.numOfPats += pat.getRepeat();
        try{
           patternList.set(index, pat);        
           
        }catch(IndexOutOfBoundsException ex){
           patternList.add(index,pat); 
        }
                
    }
    
        
    public boolean hasPatterns() {
        return patternList.size() > 0;
    }
            
}
