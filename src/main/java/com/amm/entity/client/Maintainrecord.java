package com.amm.entity.client;

/**
 * Created by 杨思名 on 2016/11/18.
 */
public class Maintainrecord {

    private String machCode;
    private String machName;
    private String workingType;
    private Integer id;
    private String terminalCode;
    private String maintainInfo;
    private Integer machId;

    public Integer getMachId() {
        return machId;
    }

    public void setMachId(Integer machId) {
        this.machId = machId;
    }

    public String getMaintainInfo() {
        return maintainInfo;
    }

    public void setMaintainInfo(String maintainInfo) {
        this.maintainInfo = maintainInfo;
    }

    public String getMachCode() {
        return machCode;
    }

    public void setMachCode(String machCode) {
        this.machCode = machCode;
    }

    public String getMachName() {
        return machName;
    }

    public void setMachName(String machName) {
        this.machName = machName;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }
}
