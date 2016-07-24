package com.amm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by csw on 2016/7/24 17:21.
 * Explain:
 */
@Entity
@Table(name = "base_org", schema = "", catalog = "amm")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseOrgEntity {
    private Integer id;
    private String orgCode;
    private String orgName;
    private String orgContact;
    private String orgAddress;
    private String orgTell;
    private String orgFax;
    private String orgEmail;
    private Collection<MachineEntity> machinesById;
    private Collection<OrgUserEntity> orgUsersById;
    private Collection<TerminalEntity> terminalsById;
    private Collection<WorkerEntity> workersById;

    @Id
    @Column(name = "id")
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
        return result;
    }

    @OneToMany(mappedBy = "baseOrgByOrgId")
    @JsonIgnore
    public Collection<MachineEntity> getMachinesById() {
        return machinesById;
    }

    public void setMachinesById(Collection<MachineEntity> machinesById) {
        this.machinesById = machinesById;
    }

    @OneToMany(mappedBy = "baseOrgByOrgId")
    @JsonIgnore
    public Collection<OrgUserEntity> getOrgUsersById() {
        return orgUsersById;
    }

    public void setOrgUsersById(Collection<OrgUserEntity> orgUsersById) {
        this.orgUsersById = orgUsersById;
    }

    @OneToMany(mappedBy = "baseOrgByOrgId")
    @JsonIgnore
    public Collection<TerminalEntity> getTerminalsById() {
        return terminalsById;
    }

    public void setTerminalsById(Collection<TerminalEntity> terminalsById) {
        this.terminalsById = terminalsById;
    }

    @OneToMany(mappedBy = "baseOrgByOrgId")
    @JsonIgnore
    public Collection<WorkerEntity> getWorkersById() {
        return workersById;
    }

    public void setWorkersById(Collection<WorkerEntity> workersById) {
        this.workersById = workersById;
    }

    public BaseOrgEntity(){}

    public BaseOrgEntity(String orgCode, String orgName, String orgContact) {
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgContact = orgContact;
    }
}
