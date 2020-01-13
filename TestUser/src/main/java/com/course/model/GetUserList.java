package com.course.model;

import lombok.Data;

@Data
public class GetUserList {
    private String id;
    private String userName;
    private String age;
    private String sex;
    private String expected;

    @Override
    public String toString() {
        return "GetUserList{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", expected='" + expected + '\'' +
                '}';
    }
}
