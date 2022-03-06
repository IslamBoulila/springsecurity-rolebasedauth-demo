package com.security.islam.security.config.security;

import com.security.islam.security.services.UserService;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;


public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {


    private ApplicationContext applicationContext;
    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    Logger logger = LoggerFactory.getLogger(CustomMethodSecurityExpressionHandler.class);

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {

        CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication);
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        root.setPermissionEvaluator(getPermissionEvaluator());

        //set the UserService in the CustomMethodSecurityExpressionRoot instance to be able to use it
        root.setUserService(this.applicationContext.getBean(UserService.class));

       logger.debug("inside createSecurityExpressionRoot, roleHierarchy: "+this.applicationContext.getBean(RoleHierarchyImpl.class));
        logger.debug("inside createSecurityExpressionRoot, roleHierarchy: "+getRoleHierarchy());

        return root;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        this.applicationContext=applicationContext;
    }
}



