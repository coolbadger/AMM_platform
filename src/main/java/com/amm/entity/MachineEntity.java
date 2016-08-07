package com.amm.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by csw on 2016/8/6 14:56.
 * Explain:
 */
@Entity
@Table(name = "machine", schema = "", catalog = "amm")
public class MachineEntity {
    private Integer id;
    private Integer orgId;
    private String machCode;
    private String machName;
    private String workingType;
    private String creator;
    private Date createTime;
    private String state;
    private boolean active = true;
    private String notes;

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
    @Column(name = "org_id")
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "active")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MachineEntity that = (MachineEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (orgId != null ? !orgId.equals(that.orgId) : that.orgId != null) return false;
        if (machCode != null ? !machCode.equals(that.machCode) : that.machCode != null) return false;
        if (machName != null ? !machName.equals(that.machName) : that.machName != null) return false;
        if (workingType != null ? !workingType.equals(that.workingType) : that.workingType != null) return false;
//        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
//        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
//        if (state != null ? !state.equals(that.state) : that.state != null) return false;
//        if (active != null ? !active.equals(that.active) : that.active != null) return false;
//        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (machCode != null ? machCode.hashCode() : 0);
        result = 31 * result + (machName != null ? machName.hashCode() : 0);
        result = 31 * result + (workingType != null ? workingType.hashCode() : 0);
//        result = 31 * result + (creator != null ? creator.hashCode() : 0);
//        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
//        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    public MachineEntity changeUpdateInfoToSave(MachineEntity updated) {

        if(updated != null) {
            updated.setMachCode(this.machCode);
            updated.setMachName(this.machName);
            updated.setWorkingType(this.workingType);
            updated.setActive(this.active);
            updated.setNotes(this.notes);
            updated.setState(this.state);
        }

        return updated;
    }
}
