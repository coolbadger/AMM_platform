package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/22.
 */
@Entity
@Table(name = "machine", schema = "", catalog = "amm")
public class MachineEntity {
    private int id;
    private String machCode;
    private String machName;
    private String workingType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        if (id != that.id) return false;
        if (machCode != null ? !machCode.equals(that.machCode) : that.machCode != null) return false;
        if (machName != null ? !machName.equals(that.machName) : that.machName != null) return false;
        if (workingType != null ? !workingType.equals(that.workingType) : that.workingType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (machCode != null ? machCode.hashCode() : 0);
        result = 31 * result + (machName != null ? machName.hashCode() : 0);
        result = 31 * result + (workingType != null ? workingType.hashCode() : 0);
        return result;
    }
}
