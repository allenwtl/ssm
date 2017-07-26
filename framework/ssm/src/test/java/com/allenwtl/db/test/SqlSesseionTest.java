package com.allenwtl.db.test;


import com.allenwtl.pojo.School;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSesseionTest {

    private static  SqlSessionFactory factory = null ;

    public static void main(String[] args) throws SQLException {

        SqlSession sqlSession = getSqlSession();

        insertData(sqlSession);

        sqlSession = getSqlSession();
        selectAll(sqlSession);

    }

    static void insertData(SqlSession sqlSession ){

        String sql = "SchoolMapper.insert";
        School school = new School();
        school.setCourse("IT1");
        school.setScore(new BigDecimal(2.3));
        school.setUserName("wtl1");
        sqlSession.insert(sql, school);
        System.out.println("insert:id->"+school.getId());
    }

    static void selectAll(SqlSession sqlSession){
        String sql = "SchoolMapper.select";

        Map<String,Object> params = new HashMap<>();
        params.put("id", 0);
        List<School> schools = sqlSession.selectList(sql, params);
        if(schools.size() > 0){
            for (School item : schools){
                System.out.println("selectAll:->"+item.getId());
            }
        }
    }

    static SqlSession getSqlSession(){
        if(factory == null){
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream("./mybatisConfig.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(inputStream);
        }
        SqlSession sqlSession = factory.openSession(TransactionIsolationLevel.READ_COMMITTED);
        return sqlSession ;
    }

}
