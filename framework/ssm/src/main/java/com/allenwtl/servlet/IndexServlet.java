package com.allenwtl.servlet;


import com.allenwtl.pojo.School;
import com.allenwtl.util.SessionList;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("p");
        if(method.equalsIgnoreCase("query")){
            query(req, resp);
        }

    }


    public void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer index = Integer.parseInt(req.getParameter("index"));
        SqlSession sqlSession = SessionList.getSession();
        String sql = "SchoolMapper.select";
        Map<String,Object> params = new HashMap<>();
        params.put("id", index);

        List<School> schoolList = sqlSession.selectList(sql, params);
        resp.getOutputStream().println(schoolList.size());

    }

}
