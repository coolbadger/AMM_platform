package com.amm.entity.client;

import java.util.Date;

/**
 * Created by 杨思名 on 2017/2/9.
 */
public class CptDetails {
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
    private Integer machSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(String orgContact) {
        this.orgContact = orgContact;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgTell() {
        return orgTell;
    }

    public void setOrgTell(String orgTell) {
        this.orgTell = orgTell;
    }

    public String getOrgFax() {
        return orgFax;
    }

    public void setOrgFax(String orgFax) {
        this.orgFax = orgFax;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getMachSize() {
        return machSize;
    }

    public void setMachSize(Integer machSize) {
        this.machSize = machSize;
    }
}
