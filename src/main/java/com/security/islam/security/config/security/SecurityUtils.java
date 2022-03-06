package com.security.islam.security.config.security;

import com.security.islam.security.utils.RoleType;
import org.apache.catalina.realm.JNDIRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


public final class SecurityUtils {

    private static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    static SecurityContext securityContextHolder = SecurityContextHolder.getContext();

    @Autowired
    private  SecurityUtils(SecurityContext securityContextHolder) {
        this.securityContextHolder = securityContextHolder;
    }

     public  static boolean isAdmin(){

         logger.debug("Authorities; ",securityContextHolder.getAuthentication().getAuthorities());

        boolean isAdmin =  securityContextHolder.getAuthentication().getAuthorities()
                .stream().anyMatch(auth->  auth.getAuthority().equals(RoleType.ROLE_ADMIN.name()) );

         logger.debug("Is admin {}",isAdmin);
       return isAdmin;
    }
}
