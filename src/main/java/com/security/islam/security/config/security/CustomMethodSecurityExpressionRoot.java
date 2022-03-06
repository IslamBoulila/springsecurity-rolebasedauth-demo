package com.security.islam.security.config.security;

import com.security.islam.security.DTOs.UserDTO;
import com.security.islam.security.services.UserService;
import com.security.islam.security.utils.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot  implements MethodSecurityExpressionOperations {


    private  UserService userService;
   //private Logger logger = LoggerFactory.getLogger(CustomMethodSecurityExpressionRoot.class);
   private HttpServletRequest request;
    private Object filterObject;
    private Object returnObject;
    private Object target;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }
    public CustomMethodSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a);
        this.request = fi.getRequest();
    }


    /**
    checks if the authenticated user have access to the user detail with the id
     Only a user have access to his own user details or is an admin
     */
    public boolean hasUser(Long id){
        UserDTO user =  this.userService.findOneById(id);

        return authentication.getName().equals(user.getUserName()) || SecurityUtils.isAdmin();
        //return authentication.getName().equals(user.getUserName());
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }

    public void setUserService(UserService userService){
        this.userService=userService;
    }
}
