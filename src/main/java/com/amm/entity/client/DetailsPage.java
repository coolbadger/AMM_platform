package com.amm.entity.client;

/**
 * Created by 杨思名 on 2017/2/24.
 */
public class DetailsPage {

    private Integer id;
    private String orgCode;
    private String orgName;
    private String orgAddress;
    private Integer machSize;
    private String allWorkArea;
    private String allDrivingArea;
    private String allWorkTime;


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public Integer getMachSize() {
        return machSize;
    }

    public void setMachSize(Integer machSize) {
        this.machSize = machSize;
    }

    public String getAllWorkArea() {
        return allWorkArea;
    }

    public void setAllWorkArea(String allWorkArea) {
        this.allWorkArea = allWorkArea;
    }

    public String getAllDrivingArea() {
        return allDrivingArea;
    }

    public void setAllDrivingArea(String allDrivingArea) {
        this.allDrivingArea = allDrivingArea;
    }

    public String getAllWorkTime() {
        return allWorkTime;
    }

    public void setAllWorkTime(String allWorkTime) {
        this.allWorkTime = allWorkTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
