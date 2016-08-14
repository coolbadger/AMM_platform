package com.amm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by csw on 2016/8/6 14:56.
 * Explain:
 */
@Entity
@Table(name = "gps_record", schema = "", catalog = "amm")
public class GpsRecordEntity {
    private Integer id;
    private Integer workerId;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "worker_id")
    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    @Basic
    @Column(name = "ref_mach_terminal_id")
    public Integer getRefMachTerminalId() {
        return refMachTerminalId;
    }

    public void setRefMachTerminalId(Integer refMachTerminalId) {
        this.refMachTerminalId = refMachTerminalId;
    }

    @Basic
    @Column(name = "terminal_code")
    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    @Basic
    @Column(name = "gps_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    @Basic
    @Column(name = "local_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Date localTime) {
        this.localTime = localTime;
    }

    @Basic
    @Column(name = "lng")
    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    @Basic
    @Column(name = "lat")
    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "alt")
    public BigDecimal getAlt() {
        return alt;
    }

    public void setAlt(BigDecimal alt) {
        this.alt = alt;
    }

    @Basic
    @Column(name = "accuracy")
    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }

    @Basic
    @Column(name = "speed")
    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
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
    @Column(name = "sensor2")
    public String getSensor2() {
        return sensor2;
    }

    public void setSensor2(String sensor2) {
        this.sensor2 = sensor2;
    }

    @Basic
    @Column(name = "sensor3")
    public String getSensor3() {
        return sensor3;
    }

    public void setSensor3(String sensor3) {
        this.sensor3 = sensor3;
    }

    @Basic
    @Column(name = "sensor4")
    public String getSensor4() {
        return sensor4;
    }

    public void setSensor4(String sensor4) {
        this.sensor4 = sensor4;
    }

    @Basic
    @Column(name = "sensor_extra")
    public String getSensorExtra() {
        return sensorExtra;
    }

    public void setSensorExtra(String sensorExtra) {
        this.sensorExtra = sensorExtra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpsRecordEntity that = (GpsRecordEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null) return false;
        if (refMachTerminalId != null ? !refMachTerminalId.equals(that.refMachTerminalId) : that.refMachTerminalId != null)
            return false;
        if (gpsTime != null ? !gpsTime.equals(that.gpsTime) : that.gpsTime != null) return false;
        if (localTime != null ? !localTime.equals(that.localTime) : that.localTime != null) return false;
        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (alt != null ? !alt.equals(that.alt) : that.alt != null) return false;
        if (accuracy != null ? !accuracy.equals(that.accuracy) : that.accuracy != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;
        if (sensor1 != null ? !sensor1.equals(that.sensor1) : that.sensor1 != null) return false;
        if (sensor2 != null ? !sensor2.equals(that.sensor2) : that.sensor2 != null) return false;
        if (sensor3 != null ? !sensor3.equals(that.sensor3) : that.sensor3 != null) return false;
        if (sensor4 != null ? !sensor4.equals(that.sensor4) : that.sensor4 != null) return false;
        if (sensorExtra != null ? !sensorExtra.equals(that.sensorExtra) : that.sensorExtra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (workerId != null ? workerId.hashCode() : 0);
        result = 31 * result + (refMachTerminalId != null ? refMachTerminalId.hashCode() : 0);
        result = 31 * result + (gpsTime != null ? gpsTime.hashCode() : 0);
        result = 31 * result + (localTime != null ? localTime.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (alt != null ? alt.hashCode() : 0);
        result = 31 * result + (accuracy != null ? accuracy.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (sensor1 != null ? sensor1.hashCode() : 0);
        result = 31 * result + (sensor2 != null ? sensor2.hashCode() : 0);
        result = 31 * result + (sensor3 != null ? sensor3.hashCode() : 0);
        result = 31 * result + (sensor4 != null ? sensor4.hashCode() : 0);
        result = 31 * result + (sensorExtra != null ? sensorExtra.hashCode() : 0);
        return result;
    }
}
