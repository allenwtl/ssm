package com.allenwtl.pojo;

import java.util.Date;

public class Book  extends Artistic{

    private final String type ;

    private String author ;

    public Book(Date publishDate, String name, String author) {
        super(publishDate, name);
        this.type = "book";
        this.author = author;
    }


    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
