/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomek
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SequencePattern.findAll", query = "SELECT s FROM SequencePattern s")
    , @NamedQuery(name = "SequencePattern.findById", query = "SELECT s FROM SequencePattern s WHERE s.id = :id")
    , @NamedQuery(name = "SequencePattern.findByPatternID", query = "SELECT s FROM SequencePattern s WHERE s.patternID = :patternID")
    , @NamedQuery(name = "SequencePattern.findBySeqNum", query = "SELECT s FROM SequencePattern s WHERE s.seqNum = :seqNum")})
public class SequencePattern implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "SequencePattern")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "Pattern_ID")
    private Integer patternID;
    private Integer seqNum;

    public SequencePattern() {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatternID() {
        return patternID;
    }

    public void setPatternID(Integer patternID) {
        this.patternID = patternID;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    @Override
    public int hashCode() {
                
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SequencePattern)) {
            return false;
        }
        return this.id.equals(((Integer)object));
    }

    @Override
    public String toString() {
        return "dao.Sequence[ sequencePK=" + id + " ]";
    }
    
}
