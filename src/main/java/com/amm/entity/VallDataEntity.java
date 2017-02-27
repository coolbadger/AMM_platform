package com.amm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 杨思名 on 2017/2/21.
 */
/*@Entity
@Table(name = "v_all_data", schema = "", catalog = "amm")*/
public class VallDataEntity {

    private String orgCode;
    private String orgName;
    private String orgAddress;
    private String userName;
    private String sensor1;
    private BigDecimal lngFixed;
    private BigDecimal latFixed;
    private Date gpsTime;
    private Date localTime;
    private String terminalName;
    private String machCode;
    private String machName;


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
    @Column(name = "org_address")
    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "sensor1")
    public String getSensor1() {
        return sensor1;
    }

    public void setSensor1(String sensor1) {
        this.sensor1 = sensor1;
    }

    @Basic
    @Column(name = "lng_fixed")
    public BigDecimal getLngFixed() {
        return lngFixed;
    }

    public void setLngFixed(BigDecimal lngFixed) {
        this.lngFixed = lngFixed;
    }

    @Basic
    @Column(name = "lat_fixed")
    public BigDecimal getLatFixed() {
        return latFixed;
    }

    public void setLatFixed(BigDecimal latFixed) {
        this.latFixed = latFixed;
    }

    @Basic
    @Column(name = "gps_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    @Basic
    @Column(name = "local_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Date localTime) {
        this.localTime = localTime;
    }

    @Basic
    @Column(name = "terminal_name")
    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    @Basic
    @Column(name = "mach_code")
    public String getMachCode() {
        return machCode;
    }

    public void setMachCode(String machCode) {
        this.machCode = machCode;
    }

    @Basic
    @Column(name = "mach_name")
    public String getMachName() {
        return machName;
    }

    public void setMachName(String machName) {
        this.machName = machName;
    }



}
