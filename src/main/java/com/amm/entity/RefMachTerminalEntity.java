package com.amm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by csw on 2016/7/25 10:26.
 * Explain:
 */
@Entity
@Table(name = "ref_mach_terminal", schema = "", catalog = "amm")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RefMachTerminalEntity {
    private Integer id;
    private String machTerminalInfo;
    private Collection<GpsRecordEntity> gpsRecordsById;
    private MachTerminalEntity machTerminalByMachTerminalId;

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
    @Column(name = "mach_terminal_info")
    public String getMachTerminalInfo() {
        return machTerminalInfo;
    }

    public void setMachTerminalInfo(String machTerminalInfo) {
        this.machTerminalInfo = machTerminalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefMachTerminalEntity that = (RefMachTerminalEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (machTerminalInfo != null ? !machTerminalInfo.equals(that.machTerminalInfo) : that.machTerminalInfo != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (machTerminalInfo != null ? machTerminalInfo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "refMachTerminalByRefMachTerminalId")
    @JsonIgnore
    public Collection<GpsRecordEntity> getGpsRecordsById() {
        return gpsRecordsById;
    }

    public void setGpsRecordsById(Collection<GpsRecordEntity> gpsRecordsById) {
        this.gpsRecordsById = gpsRecordsById;
    }

    @ManyToOne
    @JoinColumn(name = "mach_terminal_id", referencedColumnName = "id", nullable = false)
    public MachTerminalEntity getMachTerminalByMachTerminalId() {
        return machTerminalByMachTerminalId;
    }

    public void setMachTerminalByMachTerminalId(MachTerminalEntity machTerminalByMachTerminalId) {
        this.machTerminalByMachTerminalId = machTerminalByMachTerminalId;
    }
}
