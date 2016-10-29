package com.allenwtl.servlet;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.allenwtl.pojo.School;
import com.allenwtl.util.SessionList;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("p");
        if(method.equalsIgnoreCase("query")){
            query(req, resp);
        } else if(method.equalsIgnoreCase("add")){
            addSchool(req, resp);
        } else if(method.equalsIgnoreCase("updateUserName")){
            updateUserName(req, resp);
        } else if(method.equalsIgnoreCase("queryAll")){
            queryAll(req, resp);
        }

    }


    public void queryAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession = SessionList.getSession();
        String sql = "SchoolMapper.selectAll";
        List<School> schoolList = sqlSession.selectList(sql);
        JSONArray jsonArray = new JSONArray();
        for (School item :schoolList){
            jsonArray.add(JSONObject.toJSON(item, new SerializeConfig()));
        }
        resp.setHeader("Content-type", "application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        resp.getOutputStream().println(jsonArray.toJSONString());
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

    public void addSchool(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        School school = new School();
        school.setCourse("IT");
        school.setScore(new BigDecimal(78.2));
        school.setUserName("张珊");
        String sql = "SchoolMapper.insert";

        SqlSession sqlSession = SessionList.getSession();

        int result = sqlSession.insert(sql, school);

        sqlSession.commit();
        resp.getOutputStream().println(result);

    }

    public void updateUserName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        School school = new School();
        school.setCourse("IT");
        school.setScore(new BigDecimal(78.2));
        school.setUserName(userName);
        String sql = "SchoolMapper.insert";
        SqlSession sqlSession = SessionList.getSession();
        int result = sqlSession.insert(sql, school);
        sqlSession.commit();
//        String userName = req.getParameter("userName");
//        SqlSession sqlSession = SessionList.getSession();
//        System.out.println("parseToUnicode"+EmojiParser.parseToUnicode(userName));
//        System.out.println("parseToHtmlHexadecimal"+EmojiParser.parseToHtmlHexadecimal(userName));
//        System.out.println("parseToHtmlDecimal"+EmojiParser.parseToHtmlDecimal(userName));
//        School school = sqlSession.selectOne("SchoolMapper.selectUserName", userName);
        resp.getOutputStream().println(result);
    }

    public static void main(String[] args) {
        System.out.println("张三");
    }

}
