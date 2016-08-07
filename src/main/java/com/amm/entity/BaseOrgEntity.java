package com.amm.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by csw on 2016/8/6 14:56.
 * Explain:
 */
@Entity
@Table(name = "base_org", schema = "", catalog = "amm")
public class BaseOrgEntity {
    private Integer id;
    private String orgCode;
    private String orgName;
    private String orgContact;
    private String orgAddress;
    private String orgTell;
    private String orgFax;
    private String orgEmail;
    private Date createTime;
    private String creator;
    private boolean active = true;
    private String notes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "org_code")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Basic
    @Column(name = "org_name")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Basic
    @Column(name = "org_contact")
    public String getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(String orgContact) {
        this.orgContact = orgContact;
    }

    @Basic
    @Column(name = "org_address")
    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    @Basic
    @Column(name = "org_tell")
    public String getOrgTell() {
        return orgTell;
    }

    public void setOrgTell(String orgTell) {
        this.orgTell = orgTell;
    }

    @Basic
    @Column(name = "org_fax")
    public String getOrgFax() {
        return orgFax;
    }

    public void setOrgFax(String orgFax) {
        this.orgFax = orgFax;
    }

    @Basic
    @Column(name = "org_email")
    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "active")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseOrgEntity that = (BaseOrgEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orgCode != null ? !orgCode.equals(that.orgCode) : that.orgCode != null) return false;
        if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
        if (orgContact != null ? !orgContact.equals(that.orgContact) : that.orgContact != null) return false;
        if (orgAddress != null ? !orgAddress.equals(that.orgAddress) : that.orgAddress != null) return false;
        if (orgTell != null ? !orgTell.equals(that.orgTell) : that.orgTell != null) return false;
        if (orgFax != null ? !orgFax.equals(that.orgFax) : that.orgFax != null) return false;
        if (orgEmail != null ? !orgEmail.equals(that.orgEmail) : that.orgEmail != null) return false;
//        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
//        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
//        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (orgContact != null ? orgContact.hashCode() : 0);
        result = 31 * result + (orgAddress != null ? orgAddress.hashCode() : 0);
        result = 31 * result + (orgTell != null ? orgTell.hashCode() : 0);
        result = 31 * result + (orgFax != null ? orgFax.hashCode() : 0);
        result = 31 * result + (orgEmail != null ? orgEmail.hashCode() : 0);
//        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
//        result = 31 * result + (creator != null ? creator.hashCode() : 0);
//        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    public BaseOrgEntity changeUpdateInfoToSave(BaseOrgEntity updated) {

        if(updated != null) {
            updated.setOrgCode(this.orgCode);
            updated.setOrgName(this.orgName);
            updated.setOrgContact(this.orgContact);
            updated.setOrgAddress(this.orgAddress);
            updated.setOrgTell(this.orgTell);
            updated.setOrgFax(this.orgFax);
            updated.setOrgEmail(this.orgEmail);
            updated.setNotes(this.notes);
            updated.setActive(this.active);
        }

        return updated;
    }
}
