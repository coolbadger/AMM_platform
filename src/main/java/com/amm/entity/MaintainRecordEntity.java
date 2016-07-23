package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/22.
 */
@Entity
@Table(name = "maintain_record", schema = "", catalog = "amm")
public class MaintainRecordEntity {
    private int id;
    private String maintainInfo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        if (id != that.id) return false;
        if (maintainInfo != null ? !maintainInfo.equals(that.maintainInfo) : that.maintainInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (maintainInfo != null ? maintainInfo.hashCode() : 0);
        return result;
    }
}
