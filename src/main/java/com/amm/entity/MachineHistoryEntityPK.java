package com.amm.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by csw on 2016/7/22.
 */
public class MachineHistoryEntityPK implements Serializable {
    private int id;
    private int machId;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "mach_id")
    @Id
    public int getMachId() {
        return machId;
    }

    public void setMachId(int machId) {
        this.machId = machId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MachineHistoryEntityPK that = (MachineHistoryEntityPK) o;

        if (id != that.id) return false;
        if (machId != that.machId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + machId;
        return result;
    }
}
