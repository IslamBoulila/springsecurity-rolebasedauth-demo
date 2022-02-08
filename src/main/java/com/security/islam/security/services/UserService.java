package com.security.islam.security.services;

import com.security.islam.security.DTOs.UserDTO;
import com.security.islam.security.entities.Role;
import com.security.islam.security.entities.User;
import com.security.islam.security.repositories.RoleRepository;
import com.security.islam.security.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();


    public UserDTO createUser( UserDTO user){
        Role role=roleRepository.findOneByName(user.getRoleName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = modelMapper.map(user,User.class);
        newUser.setRole(role);
        User createdUser= userRepository.save(newUser);
        return modelMapper.map(createdUser,UserDTO.class);
    }

    public List<UserDTO> findAll(){
       return  userRepository.findAll().stream().map(user-> modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO update(UserDTO userDTO)  throws UsernameNotFoundException {

        User updatedUser= userRepository.findOneById(userDTO.getId())
                .map(user-> {
                    user.setUserName(userDTO.getUserName());
                return   userRepository.save(user);
                }).orElseThrow( ()->new UsernameNotFoundException("user not found"));
        return  modelMapper.map(updatedUser,UserDTO.class);

    }


}
