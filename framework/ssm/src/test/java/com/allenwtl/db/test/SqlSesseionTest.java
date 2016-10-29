package com.allenwtl.db.test;


import com.allenwtl.pojo.School;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSesseionTest {

    public static void main(String[] args) {

        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("./mybatisConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        SqlSession sqlSession = factory.openSession();
        String sql = "SchoolMapper.select";
        Map<String,Object> params = new HashMap<>();
        params.put("id", 6);

        List<School> schoolList = sqlSession.selectList(sql, params);
        for (School school : schoolList){
            System.out.println(school.toString());
        }
    }
}
