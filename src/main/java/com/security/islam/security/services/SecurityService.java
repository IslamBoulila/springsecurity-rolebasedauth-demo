package com.security.islam.security.services;

import com.security.islam.security.DTOs.UserDTO;
import com.security.islam.security.config.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.config.AuthConfig;


@Service("securityService")
public class SecurityService {

    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(SecurityService.class);
    Authentication authentication;


    public boolean hasUser(Long id){

        UserDTO user =  this.userService.findOneById(id);

        this.authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("Authentication context :"+SecurityContextHolder.getContext());

        return authentication.getName().equals(user.getUserName()) || SecurityUtils.isAdmin();
    }

}
