package com.security.islam.security.controllers;

import com.security.islam.security.DTOs.RoleDTO;
import com.security.islam.security.entities.Role;
import com.security.islam.security.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Have to be a rest controller otherwise it will launch an  TemplateInputException
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(RoleController.class);

//@Autowired
 //public RoleService roleService;

   // @Secured("ROLE_ADMIN")
    //@PostMapping("/create")
   /* public RoleDTO saveNewRole(@RequestBody RoleDTO role){
        logger.debug("Inside saveNewRole");
        return this.roleService.save(role);
    }*/
}
