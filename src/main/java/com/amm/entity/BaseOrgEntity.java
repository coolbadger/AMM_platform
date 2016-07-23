package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/22.
 */
@Entity
@Table(name = "base_org", schema = "", catalog = "amm")
public class BaseOrgEntity {
    private int id;
    private String orgCode;
    private String orgName;
    private String orgContact;
    private String orgAddress;
    private String orgTell;
    private String orgFax;
    private String orgEmail;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseOrgEntity that = (BaseOrgEntity) o;

        if (id != that.id) return false;
        if (orgCode != null ? !orgCode.equals(that.orgCode) : that.orgCode != null) return false;
        if (orgName != null ? !orgName.equals(that.orgName) : that.orgName != null) return false;
        if (orgContact != null ? !orgContact.equals(that.orgContact) : that.orgContact != null) return false;
        if (orgAddress != null ? !orgAddress.equals(that.orgAddress) : that.orgAddress != null) return false;
        if (orgTell != null ? !orgTell.equals(that.orgTell) : that.orgTell != null) return false;
        if (orgFax != null ? !orgFax.equals(that.orgFax) : that.orgFax != null) return false;
        if (orgEmail != null ? !orgEmail.equals(that.orgEmail) : that.orgEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (orgContact != null ? orgContact.hashCode() : 0);
        result = 31 * result + (orgAddress != null ? orgAddress.hashCode() : 0);
        result = 31 * result + (orgTell != null ? orgTell.hashCode() : 0);
        result = 31 * result + (orgFax != null ? orgFax.hashCode() : 0);
        result = 31 * result + (orgEmail != null ? orgEmail.hashCode() : 0);
        return result;
    }
}
