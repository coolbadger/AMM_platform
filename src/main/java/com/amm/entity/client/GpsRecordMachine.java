package com.amm.entity.client;

import com.amm.utils.DateUtil;

import java.util.Date;

/**
 * Created by csw on 2016/8/6 20:37.
 * Explain:
 */
public class GpsRecordMachine {

    private Integer reMachTerminalId;
    private Integer workerId;
    private String machCode;
    private String machName;
    private Date gpsStartTime;
    private Date gpsEndTime;

    public Integer getReMachTerminalId() {
        return reMachTerminalId;
    }

    public void setReMachTerminalId(Integer reMachTerminalId) {
        this.reMachTerminalId = reMachTerminalId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
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

    public String getGpsStartTime() {
        return gpsStartTime != null ? DateUtil.formatDate(gpsStartTime) : "";
    }

    public void setGpsStartTime(Date gpsStartTime) {
        this.gpsStartTime = gpsStartTime;
    }

    public String getGpsEndTime() {
        return gpsEndTime != null ? DateUtil.formatDate(gpsEndTime) : "";
    }

    public void setGpsEndTime(Date gpsEndTime) {
        this.gpsEndTime = gpsEndTime;
    }
}
