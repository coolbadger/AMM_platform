package com.amm.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by csw on 2016/6/7 18:27.
 * explain：基础实体类，有一个id和创建日期
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 10)
    protected Date creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @PrePersist
    public void prePersist() {
        creationTime = new Date();
    }
}
