package com.allenwtl.servlet;


import com.allenwtl.util.SessionList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;

public class MyContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ServletContext sc = servletContextEvent.getServletContext();
            InputStream inputStream = Resources.getResourceAsStream(sc.getInitParameter("contextConfigLocation"));
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(inputStream);
            SqlSession sqlSession = factory.openSession();
            SessionList.addList(sqlSession);
            System.out.println("mybatis init ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SessionList.destory();
    }
}
