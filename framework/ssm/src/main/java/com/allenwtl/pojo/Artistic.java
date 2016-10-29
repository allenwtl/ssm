package com.allenwtl.pojo;


import java.util.Date;

public class Artistic {

    private Date publishDate ;

    private String name ;


    public Artistic(Date publishDate, String name) {
        this.publishDate = publishDate;
        this.name = name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
