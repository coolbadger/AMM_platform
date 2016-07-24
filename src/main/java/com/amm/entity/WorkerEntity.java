package com.amm.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by csw on 2016/7/24 17:21.
 * Explain:
 */
@Entity
@Table(name = "worker", schema = "", catalog = "amm")
public class WorkerEntity {
    private Integer id;
    private String user;
    private String password;
    private String name;
    private String creator;
    private String post;
    private String tell;
    private String email;
    private String states;
    private String notes;
    private Collection<GpsRecordEntity> gpsRecordsById;
    private BaseOrgEntity baseOrgByOrgId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "post")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Basic
    @Column(name = "tell")
    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "states")
    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
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

        WorkerEntity that = (WorkerEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (post != null ? !post.equals(that.post) : that.post != null) return false;
        if (tell != null ? !tell.equals(that.tell) : that.tell != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (states != null ? !states.equals(that.states) : that.states != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (tell != null ? tell.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (states != null ? states.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "workerByWorkerId")
    public Collection<GpsRecordEntity> getGpsRecordsById() {
        return gpsRecordsById;
    }

    public void setGpsRecordsById(Collection<GpsRecordEntity> gpsRecordsById) {
        this.gpsRecordsById = gpsRecordsById;
    }

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    public BaseOrgEntity getBaseOrgByOrgId() {
        return baseOrgByOrgId;
    }

    public void setBaseOrgByOrgId(BaseOrgEntity baseOrgByOrgId) {
        this.baseOrgByOrgId = baseOrgByOrgId;
    }
}
