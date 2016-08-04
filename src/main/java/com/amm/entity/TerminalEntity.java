package com.amm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by csw on 2016/7/25 10:26.
 * Explain:
 */
@Entity
@Table(name = "terminal", schema = "", catalog = "amm")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TerminalEntity {
    private Integer id;
    private String terminalCode;
    private String terminalName;
    private String callNo;
    private String state;
    private String notes;
    private Collection<MachTerminalEntity> machTerminalsById;
    private BaseOrgEntity baseOrgByOrgId;
    private Collection<TerminalHistoryEntity> terminalHistoriesById;

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (terminalCode != null ? !terminalCode.equals(that.terminalCode) : that.terminalCode != null) return false;
        if (terminalName != null ? !terminalName.equals(that.terminalName) : that.terminalName != null) return false;
        if (callNo != null ? !callNo.equals(that.callNo) : that.callNo != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (terminalCode != null ? terminalCode.hashCode() : 0);
        result = 31 * result + (terminalName != null ? terminalName.hashCode() : 0);
        result = 31 * result + (callNo != null ? callNo.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "terminalByTerminalId")
    @JsonIgnore
    public Collection<MachTerminalEntity> getMachTerminalsById() {
        return machTerminalsById;
    }

    public void setMachTerminalsById(Collection<MachTerminalEntity> machTerminalsById) {
        this.machTerminalsById = machTerminalsById;
    }

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    public BaseOrgEntity getBaseOrgByOrgId() {
        return baseOrgByOrgId;
    }

    public void setBaseOrgByOrgId(BaseOrgEntity baseOrgByOrgId) {
        this.baseOrgByOrgId = baseOrgByOrgId;
    }

    @OneToMany(mappedBy = "terminalByTerminalId")
    @JsonIgnore
    public Collection<TerminalHistoryEntity> getTerminalHistoriesById() {
        return terminalHistoriesById;
    }

    public void setTerminalHistoriesById(Collection<TerminalHistoryEntity> terminalHistoriesById) {
        this.terminalHistoriesById = terminalHistoriesById;
    }

    public TerminalEntity changeUpdateInfoToSave(TerminalEntity updated) {

        if(updated != null) {
            updated.setTerminalCode(this.terminalCode);
            updated.setTerminalName(this.terminalName);
            updated.setCallNo(this.callNo);
            updated.setState(this.state);
            updated.setNotes(this.notes);
        }

        return updated;
    }
}
