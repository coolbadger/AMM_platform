package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/25 10:26.
 * Explain:
 */
@Entity
@Table(name = "maintain_record", schema = "", catalog = "amm")
public class MaintainRecordEntity {
    private Integer id;
    private String maintainInfo;
    private MachineEntity machineByMachId;

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
    @Column(name = "maintain_info")
    public String getMaintainInfo() {
        return maintainInfo;
    }

    public void setMaintainInfo(String maintainInfo) {
        this.maintainInfo = maintainInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaintainRecordEntity that = (MaintainRecordEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (maintainInfo != null ? !maintainInfo.equals(that.maintainInfo) : that.maintainInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (maintainInfo != null ? maintainInfo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "mach_id", referencedColumnName = "id", nullable = false)
    public MachineEntity getMachineByMachId() {
        return machineByMachId;
    }

    public void setMachineByMachId(MachineEntity machineByMachId) {
        this.machineByMachId = machineByMachId;
    }
}
