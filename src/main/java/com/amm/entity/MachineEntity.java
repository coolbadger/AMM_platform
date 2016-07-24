package com.amm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by csw on 2016/7/24 17:21.
 * Explain:
 */
@Entity
@Table(name = "machine", schema = "", catalog = "amm")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MachineEntity {
    private Integer id;
    private String machCode;
    private String machName;
    private String workingType;
    private Collection<MachTerminalEntity> machTerminalsById;
    private BaseOrgEntity baseOrgByOrgId;
    private Collection<MachineHistoryEntity> machineHistoriesById;
    private Collection<MaintainRecordEntity> maintainRecordsById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Basic
    @Column(name = "working_type")
    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MachineEntity that = (MachineEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (machCode != null ? !machCode.equals(that.machCode) : that.machCode != null) return false;
        if (machName != null ? !machName.equals(that.machName) : that.machName != null) return false;
        if (workingType != null ? !workingType.equals(that.workingType) : that.workingType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (machCode != null ? machCode.hashCode() : 0);
        result = 31 * result + (machName != null ? machName.hashCode() : 0);
        result = 31 * result + (workingType != null ? workingType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "machineByMachId")
    public Collection<MachTerminalEntity> getMachTerminalsById() {
        return machTerminalsById;
    }

    public void setMachTerminalsById(Collection<MachTerminalEntity> machTerminalsById) {
        this.machTerminalsById = machTerminalsById;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    public BaseOrgEntity getBaseOrgByOrgId() {
        return baseOrgByOrgId;
    }

    public void setBaseOrgByOrgId(BaseOrgEntity baseOrgByOrgId) {
        this.baseOrgByOrgId = baseOrgByOrgId;
    }

    @OneToMany(mappedBy = "machineByMachId")
    public Collection<MachineHistoryEntity> getMachineHistoriesById() {
        return machineHistoriesById;
    }

    public void setMachineHistoriesById(Collection<MachineHistoryEntity> machineHistoriesById) {
        this.machineHistoriesById = machineHistoriesById;
    }

    @OneToMany(mappedBy = "machineByMachId")
    public Collection<MaintainRecordEntity> getMaintainRecordsById() {
        return maintainRecordsById;
    }

    public void setMaintainRecordsById(Collection<MaintainRecordEntity> maintainRecordsById) {
        this.maintainRecordsById = maintainRecordsById;
    }
}
