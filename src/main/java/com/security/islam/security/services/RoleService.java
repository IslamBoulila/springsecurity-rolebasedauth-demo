package com.security.islam.security.services;


import com.security.islam.security.DTOs.RoleDTO;
import com.security.islam.security.entities.Authority;
import com.security.islam.security.entities.Role;
import com.security.islam.security.repositories.AuthorityRepository;
import com.security.islam.security.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {


    public  RoleRepository roleRepository;
    public  AuthorityRepository authorityRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, AuthorityRepository authorityRepository) {
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
    }

    ModelMapper modelMapper = new ModelMapper();

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public RoleDTO save(RoleDTO role){
        Role newRole= modelMapper.map(role, Role.class);
        Set<Authority> authorities = this.authorityRepository.findAllByIdIn(role.getAuthoritiesIds());
        newRole.setAuthorities(authorities);
        this.roleRepository.save(newRole);

        RoleDTO roleDTO=  modelMapper.map(newRole, RoleDTO.class);
        return roleDTO;
    }
}
