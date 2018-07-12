package com.amm.entity.client;

import com.amm.utils.DateUtil;

import java.util.Date;

/**
 * Created by csw on 2016/8/7 12:28.
 * Explain:
 */
public class MachTerminal {

    private Integer id;
    private Integer machId;
    private Integer terminalId;
    private Date startTime;
    private Date endTime;
    private Integer refMachTerminalId;
    private String machCode;
    private String machName;
    private String machNotes;
    private String workingType;
    private String terminalCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMachId() {
        return machId;
    }

    public void setMachId(Integer machId) {
        this.machId = machId;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public String getStartTime() {
        return startTime != null ? DateUtil.formatDate(startTime) : "";
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime != null ? DateUtil.formatDate(endTime) : "";
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getRefMachTerminalId() {
        return refMachTerminalId;
    }

    public void setRefMachTerminalId(Integer refMachTerminalId) {
        this.refMachTerminalId = refMachTerminalId;
    }

    public String getMachCode() {
        return machCode;
    }

    public void setMachCode(String machCode) {
        this.machCode = machCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getMachName() {
        return machName;
    }

    public void setMachName(String machName) {
        this.machName = machName;
    }

    public String getMachNotes() {
        return machNotes;
    }

    public void setMachNotes(String machNotes) {
        this.machNotes = machNotes;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }
}
