package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.Login;
import com.course.model.TableName;
import com.course.utils.ConfigFile;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCase {
    Logger log = Logger.getLogger(LoginCase.class);

    @BeforeTest(groups = "loginTrue",description = "测试准备工作")
    public void beforeTest() {
        TestConfig.addUserUrl = ConfigFile.getUrl(TableName.ADDUSER);
        TestConfig.loginUrl = ConfigFile.getUrl(TableName.LOGIN);
        TestConfig.updateUserUrl = ConfigFile.getUrl(TableName.UPDATEUSERINFO);
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(TableName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(TableName.GETUSERLIST);
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        log.info("addUserUrl:" + TestConfig.addUserUrl);
        log.info("loginUrl:" + TestConfig.loginUrl);
        log.info("updateUserUrl:" + TestConfig.updateUserUrl);
        log.info("getUserInfoUrl:" + TestConfig.getUserInfoUrl);
        log.info("getUserListUrl:" + TestConfig.getUserListUrl);
        ResourceBundle bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        String store=bundle.getString("login1");
    }

    @Test(groups = "loginTrue")
    public void loginTrue() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        Login login = session.selectOne("loginCase", 1);
        log.info("获取到的用例为:" + login.toString());
        log.info("登陆url:" + TestConfig.loginUrl);
        String result = getResult(login);
        log.info("实际结果" + result + ";预期结果" + login.getExpected());
        //测试报告写入日志
        Reporter.log("实际结果" + result + ";预期结果" + login.getExpected());
        Assert.assertEquals(login.getExpected(), result);
    }

    private String getResult(Login login) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName", login.getUserName());
        param.put("password", login.getPassword());
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        post.setHeader("content-type", "application/json");
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        return result;
    }
    @Test
    public void testDemo() throws IOException {
        SqlSession session=DataBaseUtil.getSqlSession();
        String url="127.0.0.1:8888/v1/login";
        //HttpPost post=new HttpPost(url);
        JSONObject login=session.selectOne("loginCase",1);
        JSONObject param=login;
        System.out.println(login.toString());
    }
}