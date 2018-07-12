package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/8/6 14:56.
 * Explain:
 */
@Entity
@Table(name = "ref_mach_terminal", schema = "", catalog = "amm")
public class RefMachTerminalEntity {
    private Integer id;
    private Integer machId;
    private String machCode;
    private String machName;
    private String machNotes;
    private String workingType;
    private String machState;
    private String terminalCode;
    private String terminalName;
    private String callNo;
    private String terminalState;
    private String workArea;
    private String drivingArea;
    private String workTime;
    private String machineryWidth;

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
    @Column(name = "mach_id")
    public Integer getMachId() {
        return machId;
    }

    public void setMachId(Integer machId) {
        this.machId = machId;
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
    @Column(name = "mach_notes")
    public String getMachNotes() {
        return machNotes;
    }

    public void setMachNotes(String machNotes) {
        this.machNotes = machNotes;
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
    @Column(name = "mach_state")
    public String getMachState() {
        return machState;
    }

    public void setMachState(String machState) {
        this.machState = machState;
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
    @Column(name = "terminal_name")
    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    @Basic
    @Column(name = "call_no")
    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    @Basic
    @Column(name = "terminal_state")
    public String getTerminalState() {
        return terminalState;
    }

    public void setTerminalState(String terminalState) {
        this.terminalState = terminalState;
    }

    @Basic
    @Column(name = "work_area")
    public String getWorkArea() {
        return workArea;
    }
    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    @Basic
    @Column(name = "driving_area")
    public String getDrivingArea() {
        return drivingArea;
    }

    public void setDrivingArea(String drivingArea) {
        this.drivingArea = drivingArea;
    }
    @Basic
    @Column(name = "work_time")
    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    @Basic
    @Column(name = "machinery_width")
    public String getMachineryWidth() {
        return machineryWidth;
    }

    public void setMachineryWidth(String machineryWidth) {
        this.machineryWidth = machineryWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefMachTerminalEntity that = (RefMachTerminalEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (machId != null ? !machId.equals(that.machId) : that.machId != null) return false;
        if (machCode != null ? !machCode.equals(that.machCode) : that.machCode != null) return false;
        if (machName != null ? !machName.equals(that.machName) : that.machName != null) return false;
        if (machNotes !=null ? !machNotes.equals(that.machNotes) : that.machNotes != null) return false;
        if (workingType != null ? !workingType.equals(that.workingType) : that.workingType != null) return false;
        if (machState != null ? !machState.equals(that.machState) : that.machState != null) return false;
        if (terminalCode != null ? !terminalCode.equals(that.terminalCode) : that.terminalCode != null) return false;
        if (terminalName != null ? !terminalName.equals(that.terminalName) : that.terminalName != null) return false;
        if (callNo != null ? !callNo.equals(that.callNo) : that.callNo != null) return false;
        if (terminalState != null ? !terminalState.equals(that.terminalState) : that.terminalState != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (machId != null ? machId.hashCode() : 0);
        result = 31 * result + (machCode != null ? machCode.hashCode() : 0);
        result = 31 * result + (machName != null ? machName.hashCode() : 0);
        result = 31 * result + (machNotes != null ?machNotes.hashCode() : 0);
        result = 31 * result + (workingType != null ? workingType.hashCode() : 0);
        result = 31 * result + (machState != null ? machState.hashCode() : 0);
        result = 31 * result + (terminalCode != null ? terminalCode.hashCode() : 0);
        result = 31 * result + (terminalName != null ? terminalName.hashCode() : 0);
        result = 31 * result + (callNo != null ? callNo.hashCode() : 0);
        result = 31 * result + (terminalState != null ? terminalState.hashCode() : 0);
        return result;
    }

    public RefMachTerminalEntity changeUpdateInfoToSave(RefMachTerminalEntity updated) {
        if(updated != null) {
            updated.setWorkingType(this.getWorkingType());
            updated.setTerminalState(this.getTerminalState());
            updated.setTerminalCode(this.getTerminalCode());
            updated.setMachName(this.getMachName());
            updated.setMachNotes(this.getMachNotes());
            updated.setCallNo(this.getCallNo());
            updated.setDrivingArea(this.drivingArea);
            updated.setMachCode(this.getMachCode());
            updated.setMachId(this.getMachId());
            updated.setTerminalName(this.getTerminalName());
            updated.setWorkArea(this.workArea);
            updated.setWorkTime(this.workTime);
            updated.setMachineryWidth(this.getMachineryWidth());
            updated.setMachState(this.getMachState());
        }
        return updated;
    }


}
