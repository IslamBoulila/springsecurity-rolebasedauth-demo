package com.security.islam.security.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "users")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

//    @Column(columnDefinition = "true")
//    private boolean isAccountNonExpired;

    @Column(columnDefinition = "true")
    private boolean  isAccountNonLocked;

//    @Column(columnDefinition = "true")
//    private boolean isCredentialsNonExpired;
//
//    @Column(columnDefinition = "true")
//    private boolean  isEnabled;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //Inject user authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
                this.getRole().getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .forEach(authorities::add);
                //.collect(Collectors.toList());

        //Add the user Role (ex: ROLE_ADMIN)
            authorities.add(new SimpleGrantedAuthority(this.getRole().getName()));

            return authorities;

    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }


    //Setters

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }


}


