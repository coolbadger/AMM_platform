package com.amm.entity.client;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by csw on 2016/8/16 15:21.
 * Explain:
 */
public class GpsRecordSave {

    private String userName;
    private Integer refMachTerminalId;
    private String terminalCode;
    private Date gpsTime;
    private Date localTime;
    private BigDecimal lng;
    private BigDecimal lat;
    private BigDecimal alt;
    private BigDecimal accuracy;
    private BigDecimal speed;
    private String sensor1;
    private String sensor2;
    private String sensor3;
    private String sensor4;
    private String sensorExtra;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRefMachTerminalId() {
        return refMachTerminalId;
    }

    public void setRefMachTerminalId(Integer refMachTerminalId) {
        this.refMachTerminalId = refMachTerminalId;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
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

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getAlt() {
        return alt;
    }

    public void setAlt(BigDecimal alt) {
        this.alt = alt;
    }

    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public String getSensor1() {
        return sensor1;
    }

    public void setSensor1(String sensor1) {
        this.sensor1 = sensor1;
    }

    public String getSensor2() {
        return sensor2;
    }

    public void setSensor2(String sensor2) {
        this.sensor2 = sensor2;
    }

    public String getSensor3() {
        return sensor3;
    }

    public void setSensor3(String sensor3) {
        this.sensor3 = sensor3;
    }

    public String getSensor4() {
        return sensor4;
    }

    public void setSensor4(String sensor4) {
        this.sensor4 = sensor4;
    }

    public String getSensorExtra() {
        return sensorExtra;
    }

    public void setSensorExtra(String sensorExtra) {
        this.sensorExtra = sensorExtra;
    }
}
