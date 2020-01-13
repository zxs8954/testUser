package com.course.model;

import lombok.Data;

@Data
public class Login {
    private String id;
    private String userName;
    private String password;
    private String expected;

    @Override
    public String toString() {
        return "Login{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", expected='" + expected + '\'' +
                '}';
    }
}
