package com.allenwtl.util;


import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class SessionList {

    public static final List<SqlSession> sqlSessionList = new ArrayList<>();

    public static void addList(SqlSession sqlSession){
        sqlSessionList.add(sqlSession);
    }

    public static SqlSession getSession(){
        return sqlSessionList.get(0);
    }

    public static void destory(){
        if(sqlSessionList != null)
            sqlSessionList.clear();
    }

}
