/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.entitites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ebubekir.gunerhanal
 */
@Entity
@Table(name = "PHONE_COUNTRY_CODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhoneCountryCode.findAll", query = "SELECT p FROM PhoneCountryCode p"),
    @NamedQuery(name = "PhoneCountryCode.findByCountry", query = "SELECT p FROM PhoneCountryCode p WHERE p.country = :country"),
    @NamedQuery(name = "PhoneCountryCode.findByDialing", query = "SELECT p FROM PhoneCountryCode p WHERE p.dialing = :dialing"),
    @NamedQuery(name = "PhoneCountryCode.findByCountryId", query = "SELECT p FROM PhoneCountryCode p WHERE p.countryId = :countryId")})
public class PhoneCountryCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "DIALING")
    private String dialing;
    @Id
    @Basic(optional = false)
    @Column(name = "COUNTRY_ID")
    private Short countryId;

    public PhoneCountryCode() {
    }

    public PhoneCountryCode(Short countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDialing() {
        return dialing;
    }

    public void setDialing(String dialing) {
        this.dialing = dialing;
    }

    public Short getCountryId() {
        return countryId;
    }

    public void setCountryId(Short countryId) {
        this.countryId = countryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryId != null ? countryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhoneCountryCode)) {
            return false;
        }
        PhoneCountryCode other = (PhoneCountryCode) object;
        if ((this.countryId == null && other.countryId != null) || (this.countryId != null && !this.countryId.equals(other.countryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "recruitment.entitites.PhoneCountryCode[ countryId=" + countryId + " ]";
    }
    
}
