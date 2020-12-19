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
 * @author ebubekir.gunerhanal
 */
@Entity
@Table(name = "APP_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a"),
    @NamedQuery(name = "AppUser.findByUserName", query = "SELECT a FROM AppUser a WHERE a.userName = :userName"),
    @NamedQuery(name = "AppUser.findByPassword", query = "SELECT a FROM AppUser a WHERE a.password = :password"),
    @NamedQuery(name = "AppUser.findByFullName", query = "SELECT a FROM AppUser a WHERE a.fullName = :fullName"),
    @NamedQuery(name = "AppUser.findByEmailOrPhone", query = "SELECT a FROM AppUser a WHERE a.emailOrPhone = :emailOrPhone"),
    @NamedQuery(name = "AppUser.findByUserId", query = "SELECT a FROM AppUser a WHERE a.userId = :userId"),
    @NamedQuery(name = "AppUser.findByCompanyName", query = "SELECT a FROM AppUser a WHERE a.companyName = :companyName"),
    @NamedQuery(name = "AppUser.findByLastLoginDate", query = "SELECT a FROM AppUser a WHERE a.lastLoginDate = :lastLoginDate")})
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "EMAIL_OR_PHONE")
    private String emailOrPhone;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private BigInteger userId;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "LAST_LOGIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    public AppUser() {
    }

    public AppUser(BigInteger userId) {
        this.userId = userId;
    }

    public AppUser(BigInteger userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "recruitment.entitites.AppUser[ userId=" + userId + " ]";
    }
    
}
