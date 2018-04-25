/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Tomek
 */
@Embeddable
public class PatternEventPK implements Serializable {

    private Integer ID;
    
    public PatternEventPK() {
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatternEventPK)) {
            return false;
        }
        PatternEventPK other = (PatternEventPK) object;
        return true;
    }

    @Override
    public String toString() {
        return "ERROR";
    }
    
}
