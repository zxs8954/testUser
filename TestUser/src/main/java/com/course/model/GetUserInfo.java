package com.course.model;

import lombok.Data;

@Data
public class GetUserInfo {
    private String userId;
    private String expected;

    @Override
    public String toString() {
        return "GetUserInfo{" +
                "userId='" + userId + '\'' +
                ", expected='" + expected + '\'' +
                '}';
    }
}
