package com.amm.entity.client;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 杨思名 on 2016/12/29.
 */
public class GpsData {
    private Integer reMachTerminalId;
    private BigDecimal lngFixed;
    private BigDecimal latFixed;
    private String sensor1;
    private Date gpsTime;
    private Date localTime;
    private String machCode;
    private String machName;
    private String workingType;
    private String orgCode;
    private String orgName;
    private String orgContact;
    private String orgAddress;
    private String orgTell;
    private String orgFax;
    private String orgEmail;
    private Date createTime;
    private String creator;
    private String userName;
    private String state;
    private String machineryWidth;


    public Integer getReMachTerminalId() {
        return reMachTerminalId;
    }

    public void setReMachTerminalId(Integer reMachTerminalId) {
        this.reMachTerminalId = reMachTerminalId;
    }

    public BigDecimal getLngFixed() {
        return lngFixed;
    }

    public void setLngFixed(BigDecimal lngFixed) {
        this.lngFixed = lngFixed;
    }

    public BigDecimal getLatFixed() {
        return latFixed;
    }

    public void setLatFixed(BigDecimal latFixed) {
        this.latFixed = latFixed;
    }

    public String getSensor1() {
        return sensor1;
    }

    public void setSensor1(String sensor1) {
        this.sensor1 = sensor1;
    }

    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    public Date getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Date localTime) {
        this.localTime = localTime;
    }

    public String getMachCode() {
        return machCode;
    }

    public void setMachCode(String machCode) {
        this.machCode = machCode;
    }

    public String getMachName() {
        return machName;
    }

    public void setMachName(String machName) {
        this.machName = machName;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMachineryWidth() {
        return machineryWidth;
    }

    public void setMachineryWidth(String machineryWidth) {
        this.machineryWidth = machineryWidth;
    }
}
