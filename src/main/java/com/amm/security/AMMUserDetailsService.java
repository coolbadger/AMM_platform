package com.amm.security;

import com.amm.entity.OrgUserEntity;
import com.amm.service.OrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by liuminhang on 16/8/8.
 */
@Component("ammUserDetailsService")
public class AMMUserDetailsService implements UserDetailsService {
    @Autowired
    private OrgUserService orgUserService;



    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loadby username:" + s);
        OrgUserEntity orgUserEntity = orgUserService.findByUserName(s);
        if(orgUserEntity == null){
            return null;
        }
        else {
            System.out.println("load success");
            return new AMMUserDetails(orgUserEntity);
        }
    }
}
