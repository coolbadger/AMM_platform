package com.amm.security;

import com.amm.entity.OrgUserEntity;
import com.amm.entity.SystemUserEntity;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liuminhang on 16/8/8.
 */
public class AMMUserDetails implements UserDetails{
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set grantedAuthority = new HashSet();
        if(orgUserEntity != null){
            grantedAuthority.add(new AMMUserRoles(AMMUserRoles.ROLE_ORG_USER));
        }
        if(systemUserEntity != null){
            grantedAuthority.add(new AMMUserRoles(AMMUserRoles.ROLE_SYS_USER));
        }
        return grantedAuthority;
    }

    private OrgUserEntity orgUserEntity;

    private SystemUserEntity systemUserEntity;

    AMMUserDetails(OrgUserEntity entity){
        this.orgUserEntity = entity;
        System.out.println("ORGUSER");
    }
    AMMUserDetails(SystemUserEntity entity){
        this.systemUserEntity = entity;
    }

    public String getPassword() {
        System.out.println("Password:" + orgUserEntity==null?systemUserEntity.getPassword():orgUserEntity.getPassword());
        return orgUserEntity==null?systemUserEntity.getPassword():orgUserEntity.getPassword();
    }

    public String getUsername() {
        return orgUserEntity==null?systemUserEntity.getUserName():orgUserEntity.getUserName();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
