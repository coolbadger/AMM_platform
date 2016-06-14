package com.amm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by csw on 2016/6/8 10:50.
 * explain：
 */
@Entity
@Table(name = "user", catalog = "amm")
public class User extends BaseEntity{

    @Column(length = 128, nullable = false)
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
