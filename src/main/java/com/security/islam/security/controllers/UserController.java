package com.security.islam.security.controllers;

import com.security.islam.security.DTOs.UserDTO;
import com.security.islam.security.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
 private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public UserDTO createNewUser(@RequestBody UserDTO user ){
        logger.debug("INSIDE createNewUser: ");
        return this.userService.createUser(user);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/get/all")
    public List<UserDTO > getAll( Authentication authentication){
        logger.debug("INSIDE get all: "+ authentication.getAuthorities());
        return this.userService.findAll();
    }

    //METHOD 1 to have a custom security method

    @PreAuthorize("hasUser(#id)")
    @GetMapping("/get/{id}")
    public UserDTO getUser( @PathVariable Long id){

        return this.userService.findOneById(id);
    }


    //METHOD 2
//    @PreAuthorize("@securityService.hasUser(#id)")
//    @GetMapping("/get/{id}")
//    public UserDTO getUser( @PathVariable Long id){
//
//        return this.userService.findOneById(id);
//    }


    /*@PreAuthorize("hasAuthority('user::update')")
    @PutMapping("/update")
    public UserDTO  updateUser( @RequestBody UserDTO user, Authentication authentication){

        return this.userService.update(user);
    }
*/
    /*@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public UserDTO  updateUser( @RequestBody UserDTO user, Authentication authentication){

        return this.userService.update(user);
    }*/

    @PreAuthorize("#user.userName==authentication.name")
    @PutMapping("/update")

    public UserDTO  updateUser( @RequestBody UserDTO user){

        return this.userService.update(user);
    }



}
