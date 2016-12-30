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
}
