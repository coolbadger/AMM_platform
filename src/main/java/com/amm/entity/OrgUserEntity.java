package com.amm.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by csw on 2016/7/25 10:26.
 * Explain:
 */
@Entity
@Table(name = "org_user", schema = "", catalog = "amm")
public class OrgUserEntity {
    private Integer id;
    private String userName;
    private String password;
    private String fullName;
    private String post;
    private String tell;
    private String email;
    private String state;
    private Date createTime;
    private String creator;
    private boolean active = true;
    private String notes;
    private BaseOrgEntity baseOrgByOrgId;

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
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    @Column(name = "active")
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

        OrgUserEntity that = (OrgUserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (post != null ? !post.equals(that.post) : that.post != null) return false;
        if (tell != null ? !tell.equals(that.tell) : that.tell != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
//        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (tell != null ? tell.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
//        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    public BaseOrgEntity getBaseOrgByOrgId() {
        return baseOrgByOrgId;
    }

    public void setBaseOrgByOrgId(BaseOrgEntity baseOrgByOrgId) {
        this.baseOrgByOrgId = baseOrgByOrgId;
    }

    public OrgUserEntity changeUpdateInfoToSave(OrgUserEntity saved) {
        if(saved != null) {
            saved.setUserName(this.userName);
            saved.setPassword(this.password);
            saved.setFullName(this.fullName);
            saved.setPost(this.post);
            saved.setEmail(this.email);
            saved.setNotes(this.notes);
            saved.setTell(this.tell);
            saved.setState(this.state);
        }
        return saved;
    }
}
