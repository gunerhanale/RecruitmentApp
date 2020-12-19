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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ebubekir
 */
@Entity
@Table(name = "APPOINTMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findById", query = "SELECT a FROM Appointment a WHERE a.id = :id"),
    @NamedQuery(name = "Appointment.findByRecordDate", query = "SELECT a FROM Appointment a WHERE a.recordDate = :recordDate"),
    @NamedQuery(name = "Appointment.findByStartDate", query = "SELECT a FROM Appointment a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "Appointment.findByFinishDate", query = "SELECT a FROM Appointment a WHERE a.finishDate = :finishDate"),
    @NamedQuery(name = "Appointment.findByCandidateId", query = "SELECT a FROM Appointment a WHERE a.candidateId = :candidateId"),
    @NamedQuery(name = "Appointment.findByCandidateName", query = "SELECT a FROM Appointment a WHERE a.candidateName = :candidateName"),
    @NamedQuery(name = "Appointment.findByPurpose", query = "SELECT a FROM Appointment a WHERE a.purpose = :purpose"),
    @NamedQuery(name = "Appointment.findByPhone", query = "SELECT a FROM Appointment a WHERE a.phone = :phone"),
    @NamedQuery(name = "Appointment.findByLastUpdate", query = "SELECT a FROM Appointment a WHERE a.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "Appointment.findByAppUserId", query = "SELECT a FROM Appointment a WHERE a.appUserId = :appUserId"),
    @NamedQuery(name = "Appointment.findByAppUserName", query = "SELECT a FROM Appointment a WHERE a.appUserName = :appUserName")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "RECORD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "FINISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    @Column(name = "CANDIDATE_ID")
    private BigInteger candidateId;
    @Column(name = "CANDIDATE_NAME")
    private String candidateName;
    @Column(name = "PURPOSE")
    private String purpose;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "APP_USER_ID")
    private BigInteger appUserId;
    @Column(name = "APP_USER_NAME")
    private String appUserName;

    public Appointment() {
    }

    public Appointment(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public BigInteger getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(BigInteger candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public BigInteger getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(BigInteger appUserId) {
        this.appUserId = appUserId;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
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
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "recruitment.entitites.Appointment[ id=" + id + " ]";
    }
    
}
