package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfo {
    private String userId;
    private String userName;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;

    @Override
    public String toString() {
        return "UpdateUserInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", permission='" + permission + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", expected='" + expected + '\'' +
                '}';
    }
}
