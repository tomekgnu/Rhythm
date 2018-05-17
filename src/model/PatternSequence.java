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

    
    private List patternList;    
    private Integer position;
    
    public PatternSequence(){
        this.patternList = new ArrayList();
    }
        
    public List getPatternList(){
        return this.patternList;
    }

    public void setPatternList(List list){
        this.patternList = list;
    }
    public void setPosition(Integer num){
        this.position = num;
    }
    
    public Integer getPosition(){
        return this.position;
    }
    
    public Pattern getPatternAt(int index){
        if(patternList.size() > 0)
            return (Pattern)patternList.get(index);
        return null;
    }
    
    public void addPattern(int index,Pattern pat){
        patternList.add(index,pat);
    }
    
    public void setPattern(int index,Pattern pat){
        patternList.set(index,pat);
    }
    
    public boolean hasPatterns() {
        return patternList.size() > 0;
    }
            
}
