package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/22.
 */
@Entity
@Table(name = "terminal_history", schema = "", catalog = "amm")
public class TerminalHistoryEntity {
    private int id;
    private String editInfo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "edit_info")
    public String getEditInfo() {
        return editInfo;
    }

    public void setEditInfo(String editInfo) {
        this.editInfo = editInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerminalHistoryEntity that = (TerminalHistoryEntity) o;

        if (id != that.id) return false;
        if (editInfo != null ? !editInfo.equals(that.editInfo) : that.editInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (editInfo != null ? editInfo.hashCode() : 0);
        return result;
    }
}
