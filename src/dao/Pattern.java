/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

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
    , @NamedQuery(name = "Pattern.findByDivision", query = "SELECT p FROM Pattern p WHERE p.division = :division")
    , @NamedQuery(name = "Pattern.findByDuration", query = "SELECT p FROM Pattern p WHERE p.duration = :duration")})
public class Pattern implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatternPK patternPK;    
    private Integer id;
    private Integer beats;
    private Integer division;
    private Integer duration;

    public Pattern() {
    }

    public Pattern(PatternPK patternPK) {
        this.patternPK = patternPK;
    }

    public PatternPK getPatternPK() {
        return patternPK;
    }

    public void setPatternPK(PatternPK patternPK) {
        this.patternPK = patternPK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeats() {
        return beats;
    }

    public void setBeats(Integer beats) {
        this.beats = beats;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patternPK != null ? patternPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pattern)) {
            return false;
        }
        Pattern other = (Pattern) object;
        if ((this.patternPK == null && other.patternPK != null) || (this.patternPK != null && !this.patternPK.equals(other.patternPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Pattern[ patternPK=" + patternPK + " ]";
    }
    
}
