package com.security.islam.security.utils;

public enum RoleType {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_STUDENT("ROLE_STUDENT");


    private String roleName;

    RoleType(String name) {
        this.roleName=name;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
