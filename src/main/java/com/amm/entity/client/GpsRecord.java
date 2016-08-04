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
    private Integer alt;
    private Integer accuracy;
    private Integer speed;
    private Integer workerId;
    private Integer baseOrgId;
    private Integer refMachTerminalByRefMachTerminalId;
    private Integer machTerminalByMachTerminalId;
    private Integer machineByMachId;
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

    public Integer getAlt() {
        return alt;
    }

    public void setAlt(Integer alt) {
        this.alt = alt;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getBaseOrgId() {
        return baseOrgId;
    }

    public void setBaseOrgId(Integer baseOrgId) {
        this.baseOrgId = baseOrgId;
    }

    public Integer getRefMachTerminalByRefMachTerminalId() {
        return refMachTerminalByRefMachTerminalId;
    }

    public void setRefMachTerminalByRefMachTerminalId(Integer refMachTerminalByRefMachTerminalId) {
        this.refMachTerminalByRefMachTerminalId = refMachTerminalByRefMachTerminalId;
    }

    public Integer getMachTerminalByMachTerminalId() {
        return machTerminalByMachTerminalId;
    }

    public void setMachTerminalByMachTerminalId(Integer machTerminalByMachTerminalId) {
        this.machTerminalByMachTerminalId = machTerminalByMachTerminalId;
    }

    public Integer getMachineByMachId() {
        return machineByMachId;
    }

    public void setMachineByMachId(Integer machineByMachId) {
        this.machineByMachId = machineByMachId;
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
}
