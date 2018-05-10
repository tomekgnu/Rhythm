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
import javax.persistence.OneToMany;
import misc.MidiEvent;

/**
 *
 * @author Tomek
 */
@Entity
public class PatternSequence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany( targetEntity=Pattern.class )
    private List patternList;
    
    private Integer position;
    
    public PatternSequence(){
        this.patternList = new ArrayList();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatternSequence)) {
            return false;
        }
        PatternSequence other = (PatternSequence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Sequence[ id=" + id + " ]";
    }
        
}
