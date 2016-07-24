package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/24 17:21.
 * Explain:
 */
@Entity
@Table(name = "gps_record", schema = "", catalog = "amm")
public class GpsRecordEntity {
    private Integer id;
    private String position;
    private WorkerEntity workerByWorkerId;
    private RefMachTerminalEntity refMachTerminalByRefMachTerminalId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpsRecordEntity that = (GpsRecordEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    public WorkerEntity getWorkerByWorkerId() {
        return workerByWorkerId;
    }

    public void setWorkerByWorkerId(WorkerEntity workerByWorkerId) {
        this.workerByWorkerId = workerByWorkerId;
    }

    @ManyToOne
    @JoinColumn(name = "ref_mach_terminal_id", referencedColumnName = "id", nullable = false)
    public RefMachTerminalEntity getRefMachTerminalByRefMachTerminalId() {
        return refMachTerminalByRefMachTerminalId;
    }

    public void setRefMachTerminalByRefMachTerminalId(RefMachTerminalEntity refMachTerminalByRefMachTerminalId) {
        this.refMachTerminalByRefMachTerminalId = refMachTerminalByRefMachTerminalId;
    }
}
