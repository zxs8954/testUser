package com.course.utils;

import com.course.model.TableName;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(TableName name) {
        Logger log =Logger.getLogger(ConfigFile.class);
        String url = bundle.getString("test");
        String uri="";
        String testUrl;
        if(name==TableName.LOGIN){
            uri=bundle.getString("login");
        }
        if(name==TableName.ADDUSER){
            uri=bundle.getString("addUser");
        }
        if(name==TableName.GETUSERINFO){
            uri=bundle.getString("getUserInfo");
        }
        if(name==TableName.GETUSERLIST){
            uri=bundle.getString("getUserList");
        }
        if(name==TableName.UPDATEUSERINFO){
            uri=bundle.getString("updateUserInfo");
        }
        testUrl=url+uri;
        log.info("获取的url为:"+testUrl);
        return testUrl;
    }
}
