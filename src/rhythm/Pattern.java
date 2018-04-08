/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomek
 */
@Entity
@Table(name = "Pattern")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pattern.findAll", query = "SELECT p FROM Pattern p")
    , @NamedQuery(name = "Pattern.findById", query = "SELECT p FROM Pattern p WHERE p.id = :id")
    , @NamedQuery(name = "Pattern.findByBeats", query = "SELECT p FROM Pattern p WHERE p.beats = :beats")
    , @NamedQuery(name = "Pattern.findByDivision", query = "SELECT p FROM Pattern p WHERE p.division = :division")
    , @NamedQuery(name = "Pattern.findByResolution", query = "SELECT p FROM Pattern p WHERE p.resolution = :resolution")
    , @NamedQuery(name = "Pattern.findByDuration", query = "SELECT p FROM Pattern p WHERE p.duration = :duration")
    , @NamedQuery(name = "Pattern.findByData", query = "SELECT p FROM Pattern p WHERE p.data = :data")})
public class Pattern implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatternPK patternPK;
    @GeneratedValue(generator="sqlite_pattern")
    @TableGenerator(name="sqlite_pattern", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq",pkColumnValue="Pattern",allocationSize = 1,initialValue = 1)
    @Column(name = "ID")    
    private Integer id;
    @Column(name = "Beats")
    private Integer beats;
    @Column(name = "Division")
    private Integer division;
    @Column(name = "Resolution")
    private Integer resolution;
    @Column(name = "Duration")
    private Integer duration;
    @Column(name = "Data")
    private String data;

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

    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        return "rhythm.Pattern[ patternPK=" + patternPK + " ]";
    }
    
}
