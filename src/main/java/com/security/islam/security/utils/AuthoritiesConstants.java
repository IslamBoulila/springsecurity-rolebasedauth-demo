package com.security.islam.security.utils;

public enum AuthoritiesConstants {

    student_read("student::read"),
    student_write("student::create"),

    user_read("user::read"),
    user_write("user::create"),
    user_update("user::update");

    private String authorityName;

    AuthoritiesConstants(String authorityName) {
        this.authorityName=authorityName;
    }


    @Override
    public String toString() {
        return authorityName;
    }
}

