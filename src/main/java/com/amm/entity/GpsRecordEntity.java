package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/22.
 */
@Entity
@Table(name = "gps_record", schema = "", catalog = "amm")
public class GpsRecordEntity {
    private int id;
    private String position;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpsRecordEntity that = (GpsRecordEntity) o;

        if (id != that.id) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
