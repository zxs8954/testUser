package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserList;
import com.course.model.Login;
import com.course.model.TableName;
import com.course.model.User;
import com.course.utils.ConfigFile;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GetUserListCase {

    @Test(dependsOnGroups = "loginTrue")
    public void getUserList() throws IOException {
        Logger log = Logger.getLogger(GetUserList.class);
        SqlSession session = DataBaseUtil.getSqlSession();
        GetUserList getUserList = session.selectOne("getUserListCase", 1);
        JSONArray resultJson = getResult(getUserList);
        List<User> userList = session.selectList("getUserList", getUserList);
        for (User u : userList) {
            System.out.println("list获取的user" + u.toString());
        }
        JSONArray userListJson=new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for(int i = 0;i<resultJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());
        }
    }

    private JSONArray getResult(GetUserList getUserList) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName", getUserList.getUserName());
        param.put("sex", getUserList.getSex());
        param.put("age", getUserList.getAge());
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONArray array = new JSONArray(result);
        return array;
    }
}
