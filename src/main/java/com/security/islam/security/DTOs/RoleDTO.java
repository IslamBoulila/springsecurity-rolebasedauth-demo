package com.security.islam.security.DTOs;


import java.util.List;
import java.util.Set;

public class RoleDTO {

    //private Integer id=null;
    private String name;
    public List<Long> authoritiesIds;//list  of authorities id's (permissions)
    private List<AuthorityDTO> authorities;

   /* public RoleDTO(Integer id, String name , List<AuthorityDTO> authorities) {
        this.id = id;
        this.name = name;
        this.authorities= authorities;
    }*/
   public RoleDTO( String name , List<AuthorityDTO> authorities) {
       this.name = name;
       this.authorities = authorities;
   }
    public RoleDTO(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

    public List<Long> getAuthoritiesIds() {
        return authoritiesIds;
    }


}
