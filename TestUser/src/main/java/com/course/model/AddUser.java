package com.course.model;

import lombok.Data;

@Data
public class AddUser {
    private String id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String expected;
    private String isDelete;

    @Override
    public String toString() {
        return "AddUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", permission='" + permission + '\'' +
                ", expected='" + expected + '\'' +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }
}
