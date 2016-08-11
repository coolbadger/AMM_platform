package com.amm.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by liuminhang on 16/8/8.
 */
public class AMMUserRoles implements GrantedAuthority {

    public static String ROLE_SYS_USER = "ROLE_SYS_USER";
    public static String ROLE_ORG_USER = "ROLE_ORG_USER";

    private String roleType;

    AMMUserRoles(String type){
        this.roleType = type;
    }

    public String getAuthority() {
        return roleType;
    }
}
