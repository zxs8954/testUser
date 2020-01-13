package com.course.config;

import lombok.Data;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
@Data
public class TestConfig {
    public static String loginUrl;
    public static String addUserUrl;
    public static String updateUserUrl;
    public static String getUserInfoUrl;
    public static String getUserListUrl;
    public static CookieStore store;
    public static DefaultHttpClient defaultHttpClient;
}
