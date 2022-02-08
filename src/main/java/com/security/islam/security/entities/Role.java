package com.security.islam.security.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    //There is a role_id foreing key in user table
    @OneToMany(mappedBy = "role")
    private Set<User> users;


    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))//the role class is the owner of the relationship
    Set<Authority> authorities;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
