package com.security.islam.security.DTOs;

import com.security.islam.security.entities.Authority;

public class UserDTO {

    Long id;
    String userName;
    String roleName;
    RoleDTO role; // role and authorities
    String password;
    boolean isAccountNotLocked;

    public UserDTO(Long id, String userName, String roleName, String password, Boolean isAccountNotLocked) {
        this.id = id;
        this.userName = userName;
        //this.roleId = roleId;
        this.roleName = roleName;
        this.password = password;
        this.isAccountNotLocked = isAccountNotLocked;
    }
    public UserDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   // public Long getRoleId() { return roleId; }

    //public void setRoleId(Long roleId) { this.roleId = roleId; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public boolean isAccountNotLocked() {
        return isAccountNotLocked;
    }

    public void setAccountNotLocked(boolean accountNotLocked) {
        isAccountNotLocked = accountNotLocked;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
