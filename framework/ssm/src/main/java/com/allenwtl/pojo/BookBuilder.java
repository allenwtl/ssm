package com.allenwtl.pojo;

import java.util.Date;

public class BookBuilder {
    private Date publishDate;
    private String name;
    private String author;

    public BookBuilder setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public BookBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Book createBook() {
        return new Book(publishDate, name, author);
    }
}