package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class DataBaseUtil {
    public static SqlSession getSqlSession() throws IOException {
        Logger log =Logger.getLogger(DataBaseUtil.class);
       Reader reader=Resources.getResourceAsReader("DataBase.xml");
       SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
       SqlSession sqlSession=sqlSessionFactory.openSession();
       log.info("获取sqlSession");
       return sqlSession;
    }

}
