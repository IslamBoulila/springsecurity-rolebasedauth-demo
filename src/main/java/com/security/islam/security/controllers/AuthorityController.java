package com.security.islam.security.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/authority")
public class AuthorityController {


    Logger logger = LoggerFactory.getLogger(UserController.class);

    /*@Secured("ADMIN_ROLE")
    @PostMapping("/create")
    public Authority createAuthority(@RequestBody UserDTO user , Authentication authentication){
        logger.debug("INSIDE createNewUser: "+ authentication.getAuthorities());
        return this.userService.createUser(user);
    }*/
}
