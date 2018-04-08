/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "Sequence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sequence.findAll", query = "SELECT s FROM Sequence s")
    , @NamedQuery(name = "Sequence.findById", query = "SELECT s FROM Sequence s WHERE s.id = :id")
    , @NamedQuery(name = "Sequence.findByPatternID", query = "SELECT s FROM Sequence s WHERE s.patternID = :patternID")})
public class Sequence implements Serializable {

    private static final long serialVersionUID = 11L;
    @EmbeddedId
    protected SequencePK sequencePK;
    @GeneratedValue(generator="sqlite_seq")
    @TableGenerator(name="sqlite_seq", table="sqlite_sequence",
    pkColumnName="name", valueColumnName="seq",pkColumnValue="Sequence",allocationSize = 1,initialValue = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Pattern_ID")
    private Integer patternID;

    public Sequence() {
    }

    public Sequence(SequencePK sequencePK) {
        this.sequencePK = sequencePK;
    }

    public Sequence(SequencePK sequencePK, int id) {
        this.sequencePK = sequencePK;
        this.id = id;
    }

    public SequencePK getSequencePK() {
        return sequencePK;
    }

    public void setSequencePK(SequencePK sequencePK) {
        this.sequencePK = sequencePK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPatternID() {
        return patternID;
    }

    public void setPatternID(Integer patternID) {
        this.patternID = patternID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sequencePK != null ? sequencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sequence)) {
            return false;
        }
        Sequence other = (Sequence) object;
        if ((this.sequencePK == null && other.sequencePK != null) || (this.sequencePK != null && !this.sequencePK.equals(other.sequencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rhythm.Sequence[ sequencePK=" + sequencePK + " ]";
    }
    
}
