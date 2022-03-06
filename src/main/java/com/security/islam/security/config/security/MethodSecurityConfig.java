package com.security.islam.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    /**
     * RoleHierarchyImpl & RoleVoter are 2 beans implemented in another config class to have a role Hierarchy as needed in the app
     */
    @Autowired
    private RoleHierarchyImpl roleHierarchy;
    @Autowired
    private RoleVoter roleVoter;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        //DefaultMethodSecurityExpressionHandler expressionHandler = (DefaultMethodSecurityExpressionHandler) super.createExpressionHandler();
       var expressionHandler = new CustomMethodSecurityExpressionHandler();

                expressionHandler.setRoleHierarchy(roleHierarchy);
                expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;

    }

    /**
     * AccessDecisionManager makes a final access control (authorization) decision.
     * Overriding this method is necessary for role hierarchy, customized security methods..
     * @return
     */
    @Override
    protected AccessDecisionManager accessDecisionManager() {
        //AffirmativeBased manager = (AffirmativeBased)super.accessDecisionManager() ;
        //  manager.getDecisionVoters().clear();

        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();

        var expresionAdvice= new ExpressionBasedPreInvocationAdvice();
        expresionAdvice.setExpressionHandler(getExpressionHandler());

        decisionVoters.add(new PreInvocationAuthorizationAdviceVoter(expresionAdvice));
        decisionVoters.add(roleVoter); //Add the customized RoleVoter Bean
        decisionVoters.add(new AuthenticatedVoter()); //It is necessary to add this one when we override the default AccessDecisionManager

      /*  manager.getDecisionVoters().add(roleVoter);
        manager.getDecisionVoters().add(new AuthenticatedVoter());

        manager.getDecisionVoters().add(new PreInvocationAuthorizationAdviceVoter(expresionAdvice));
        */
       return new AffirmativeBased(decisionVoters);

    }

    /*private WebExpressionVoter webExpressionVoter() {
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(defaultWebSecurityExpressionHandler);
       return  webExpressionVoter;
    }
*/

    /**
     * How to change the default access decision manager in Spring security
     * @return
     */
  /*  @Override
    protected AccessDecisionManager accessDecisionManager() {
        List < AccessDecisionVoter << ? extends Object >> decisionVoters = Arrays.asList(
                new AuthenticatedVoter(),
                new RoleVoter(),
                new WebExpressionVoter()
        );
        return new UnanimousBased(decisionVoters);
    }*/
}
