package com.amm.entity.client;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by csw on 2016/8/4 11:49.
 * Explain:
 */
public class GpsRecord {

    private Integer id;

    private Date gpsTime;
    private Date localTime;
    private BigDecimal lng;
    private BigDecimal lat;
    private BigDecimal alt;
    private BigDecimal accuracy;
    private BigDecimal speed;
    private Integer workerId;
    private Integer refMachTerminalId;
    private String machCode;
    private String machName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String  getGpsTime() {
        return gpsTime != null ? gpsTime.toString() : "";
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getLocalTime() {
        return localTime != null ? localTime.toString() : "";
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

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
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

    public Integer getRefMachTerminalId() {
        return refMachTerminalId;
    }

    public void setRefMachTerminalId(Integer refMachTerminalId) {
        this.refMachTerminalId = refMachTerminalId;
    }
}
