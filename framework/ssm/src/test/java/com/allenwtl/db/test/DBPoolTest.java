package com.allenwtl.db.test;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DBPoolTest {

    public static void main(String[] args) {
        DBPoolTest dbPoolTest = new DBPoolTest();
        InputStream inputStream = dbPoolTest.getClass().getResourceAsStream("/db-config.properties");

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        DruidDataSource druidDataSource = new DruidDataSource();

        try {
            config(druidDataSource, properties);
            druidDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void config(DruidDataSource druidDataSource, Properties properties){

        try {
            druidDataSource.setDriverClassName(properties.getProperty("driverClassName"));
            druidDataSource.setUrl(properties.getProperty("url"));
            druidDataSource.setFilters(properties.getProperty("filters"));
            druidDataSource.setUsername(properties.getProperty("username"));

            druidDataSource.setInitialSize(Integer.parseInt(properties.getProperty("initialSize")));
            druidDataSource.setMaxActive(Integer.parseInt(properties.getProperty("maxActive")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
