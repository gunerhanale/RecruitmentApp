/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.entitites;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ebubekir.gunerhanal
 */
@Entity
@Table(name = "CANDIDATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Candidates.findAll", query = "SELECT c FROM Candidates c"),
    @NamedQuery(name = "Candidates.findById", query = "SELECT c FROM Candidates c WHERE c.id = :id"),
    @NamedQuery(name = "Candidates.findByFullName", query = "SELECT c FROM Candidates c WHERE c.fullName = :fullName"),
    @NamedQuery(name = "Candidates.findByEmailAddres", query = "SELECT c FROM Candidates c WHERE c.emailAddres = :emailAddres"),
    @NamedQuery(name = "Candidates.findByMobilePhoneNumber", query = "SELECT c FROM Candidates c WHERE c.mobilePhoneNumber = :mobilePhoneNumber"),
    @NamedQuery(name = "Candidates.findByMobileCountryCode", query = "SELECT c FROM Candidates c WHERE c.mobileCountryCode = :mobileCountryCode"),
    @NamedQuery(name = "Candidates.findByRecordTime", query = "SELECT c FROM Candidates c WHERE c.recordTime = :recordTime"),
    @NamedQuery(name = "Candidates.findByExplanation", query = "SELECT c FROM Candidates c WHERE c.explanation = :explanation")})
public class Candidates implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "EMAIL_ADDRES")
    private String emailAddres;
    @Column(name = "MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;
    @Column(name = "MOBILE_COUNTRY_CODE")
    private Short mobileCountryCode;
    @Lob
    @Column(name = "RESUME")
    private Serializable resume;
    @Column(name = "RECORD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordTime;
    @Column(name = "EXPLANATION")
    private String explanation;

    public Candidates() {
    }

    public Candidates(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddres() {
        return emailAddres;
    }

    public void setEmailAddres(String emailAddres) {
        this.emailAddres = emailAddres;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Short getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(Short mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public Serializable getResume() {
        return resume;
    }

    public void setResume(Serializable resume) {
        this.resume = resume;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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
        if (!(object instanceof Candidates)) {
            return false;
        }
        Candidates other = (Candidates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "recruitment.entitites.Candidates[ id=" + id + " ]";
    }
    
}
