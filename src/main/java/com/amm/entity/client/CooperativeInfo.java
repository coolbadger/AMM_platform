package com.amm.entity.client;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 杨思名 on 2017/2/8.
 */
public class CooperativeInfo {
    private Integer id;
    private String machCode;
    private String machName;
    private String workingType;
    private String terminalCode;
    private String terminalName;
    private String localTime;
    private String orgName;
    private String orgAddress;
    private String sensor1;
    private BigDecimal FirstLngFixed;
    private BigDecimal FirstLatFixed;
    private String currentPosition;
    private String machineryWidth;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getSensor1() {
        return sensor1;
    }

    public void setSensor1(String sensor1) {
        this.sensor1 = sensor1;
    }


    public BigDecimal getFirstLngFixed() {
        return FirstLngFixed;
    }

    public void setFirstLngFixed(BigDecimal firstLngFixed) {
        FirstLngFixed = firstLngFixed;
    }

    public BigDecimal getFirstLatFixed() {
        return FirstLatFixed;
    }

    public void setFirstLatFixed(BigDecimal firstLatFixed) {
        FirstLatFixed = firstLatFixed;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getMachineryWidth() {
        return machineryWidth;
    }

    public void setMachineryWidth(String machineryWidth) {
        this.machineryWidth = machineryWidth;
    }
}
