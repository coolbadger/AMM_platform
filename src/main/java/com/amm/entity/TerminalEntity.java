package com.amm.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by csw on 2016/8/6 14:56.
 * Explain:
 */
@Entity
@Table(name = "terminal", schema = "", catalog = "amm")
public class TerminalEntity {
    private Integer id;
    private Integer orgId;
    private String terminalCode;
    private String terminalName;
    private String callNo;
    private String creator;
    private Date createTime;
    private String state;
    private String notes;
    private boolean active = true;

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
    @Column(name = "org_id")
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Basic
    @Column(name = "active")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerminalEntity that = (TerminalEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (orgId != null ? !orgId.equals(that.orgId) : that.orgId != null) return false;
        if (terminalCode != null ? !terminalCode.equals(that.terminalCode) : that.terminalCode != null) return false;
        if (terminalName != null ? !terminalName.equals(that.terminalName) : that.terminalName != null) return false;
        if (callNo != null ? !callNo.equals(that.callNo) : that.callNo != null) return false;
//        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
//        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
//        if (state != null ? !state.equals(that.state) : that.state != null) return false;
//        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
//        if (active != null ? !active.equals(that.active) : that.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (terminalCode != null ? terminalCode.hashCode() : 0);
        result = 31 * result + (terminalName != null ? terminalName.hashCode() : 0);
        result = 31 * result + (callNo != null ? callNo.hashCode() : 0);
//        result = 31 * result + (creator != null ? creator.hashCode() : 0);
//        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
//        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    public TerminalEntity changeUpdateInfoToSave(TerminalEntity updated) {

        if(updated != null) {
            updated.setTerminalName(this.terminalName);
            updated.setTerminalCode(this.terminalCode);
            updated.setCallNo(this.callNo);
            updated.setState(this.state);
            updated.setNotes(this.notes);
            updated.setActive(this.active);
        }

        return updated;
    }
}
