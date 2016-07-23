package com.amm.entity;

import javax.persistence.*;

/**
 * Created by csw on 2016/7/22.
 */
@Entity
@Table(name = "terminal", schema = "", catalog = "amm")
public class TerminalEntity {
    private int id;
    private String terminalCode;
    private String terminalName;
    private String callNo;
    private String state;
    private String notes;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerminalEntity that = (TerminalEntity) o;

        if (id != that.id) return false;
        if (terminalCode != null ? !terminalCode.equals(that.terminalCode) : that.terminalCode != null) return false;
        if (terminalName != null ? !terminalName.equals(that.terminalName) : that.terminalName != null) return false;
        if (callNo != null ? !callNo.equals(that.callNo) : that.callNo != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (terminalCode != null ? terminalCode.hashCode() : 0);
        result = 31 * result + (terminalName != null ? terminalName.hashCode() : 0);
        result = 31 * result + (callNo != null ? callNo.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
