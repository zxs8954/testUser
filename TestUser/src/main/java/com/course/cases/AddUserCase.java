package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.AddUser;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class AddUserCase {
    Logger log = Logger.getLogger(AddUserCase.class);

    @Test(dependsOnGroups = "loginTrue", description = "添加用户")
    public void addUser() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        AddUser addUser = session.selectOne("addUserCase", 1);
        log.info("获取到的测试用例是:" + addUser);
        log.info("添加用户url:"+TestConfig.addUserUrl);
        String result = getResult(addUser);
        List user =session.selectList("addUser",addUser);
        log.info("添加的用户信息:"+user.toString());
        System.out.println("---------------------");
        Assert.assertEquals(addUser.getExpected(),result);

    }

    private String getResult(AddUser addUser) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName", addUser.getUserName());
        param.put("password", addUser.getPassword());
        param.put("age", addUser.getAge());
        param.put("sex", addUser.getSex());
        param.put("permission",addUser.getPermission());
        param.put("isDelete",addUser.getIsDelete());
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        post.setHeader("content-type", "application/json");
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        return result;
    }
}
