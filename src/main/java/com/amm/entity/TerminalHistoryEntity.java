package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/25 10:26.
 * Explain:
 */
@Entity
@Table(name = "terminal_history", schema = "", catalog = "amm")
public class TerminalHistoryEntity {
    private Integer id;
    private String editInfo;
    private TerminalEntity terminalByTerminalId;

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (editInfo != null ? !editInfo.equals(that.editInfo) : that.editInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (editInfo != null ? editInfo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "terminal_id", referencedColumnName = "id", nullable = false)
    public TerminalEntity getTerminalByTerminalId() {
        return terminalByTerminalId;
    }

    public void setTerminalByTerminalId(TerminalEntity terminalByTerminalId) {
        this.terminalByTerminalId = terminalByTerminalId;
    }
}
