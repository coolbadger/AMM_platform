package com.amm.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by csw on 2016/7/25 10:26.
 * Explain:
 */
@Entity
@Table(name = "gps_record", schema = "", catalog = "amm")
public class GpsRecordEntity {
    private Integer id;
    private Date gpsTime;
    private Date localTime;
    private BigDecimal lng;
    private BigDecimal lat;
    private Integer alt;
    private Integer accuracy;
    private Integer speed;
    private WorkerEntity workerByWorkerId;
    private RefMachTerminalEntity refMachTerminalByRefMachTerminalId;

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
    @Column(name = "gps_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    @Basic
    @Column(name = "local_time")
    @Temporal(TemporalType.TIMESTAMP)
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
    public Integer getAlt() {
        return alt;
    }

    public void setAlt(Integer alt) {
        this.alt = alt;
    }

    @Basic
    @Column(name = "accuracy")
    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    @Basic
    @Column(name = "speed")
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpsRecordEntity that = (GpsRecordEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (gpsTime != null ? !gpsTime.equals(that.gpsTime) : that.gpsTime != null) return false;
        if (localTime != null ? !localTime.equals(that.localTime) : that.localTime != null) return false;
        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (alt != null ? !alt.equals(that.alt) : that.alt != null) return false;
        if (accuracy != null ? !accuracy.equals(that.accuracy) : that.accuracy != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gpsTime != null ? gpsTime.hashCode() : 0);
        result = 31 * result + (localTime != null ? localTime.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (alt != null ? alt.hashCode() : 0);
        result = 31 * result + (accuracy != null ? accuracy.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    public WorkerEntity getWorkerByWorkerId() {
        return workerByWorkerId;
    }

    public void setWorkerByWorkerId(WorkerEntity workerByWorkerId) {
        this.workerByWorkerId = workerByWorkerId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ref_mach_terminal_id", referencedColumnName = "id", nullable = false)
    public RefMachTerminalEntity getRefMachTerminalByRefMachTerminalId() {
        return refMachTerminalByRefMachTerminalId;
    }

    public void setRefMachTerminalByRefMachTerminalId(RefMachTerminalEntity refMachTerminalByRefMachTerminalId) {
        this.refMachTerminalByRefMachTerminalId = refMachTerminalByRefMachTerminalId;
    }
}
