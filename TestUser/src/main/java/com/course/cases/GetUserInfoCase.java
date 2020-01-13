package com.course.cases;


import com.course.config.TestConfig;
import com.course.model.GetUserInfo;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GetUserInfoCase {
    @Test
    public void getUserInfo() throws IOException {
        Logger log = Logger.getLogger(GetUserInfo.class);
        SqlSession session = DataBaseUtil.getSqlSession();
        GetUserInfo getUserInfo = session.selectOne("getUserInfoCase", 1);
        log.info("获取的用例为:" + getUserInfo.toString());
        JSONArray resultJson = getResultJson(getUserInfo);
        User user = session.selectOne("getUserInfo", getUserInfo);
        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        JSONArray jsonArray1 = new JSONArray(resultJson.getString(0));
        Assert.assertEquals(jsonArray.toString(), jsonArray1.toString());

    }
    private JSONArray getResultJson(GetUserInfo getUserInfo) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id", getUserInfo.getUserId());
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        post.setHeader("content-type", "application/json");
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        List list = Arrays.asList(result);
        JSONArray array = new JSONArray(list);
        return array;
    }
}
