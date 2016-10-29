package com.allenwtl.pojo;


import java.io.Serializable;
import java.math.BigDecimal;

public class School implements Serializable {

    private static final long serialVersionUID = -365128143416660201L;

    private Integer id ;

    private String userName ;

    private String course ;

    private BigDecimal score ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "id:="+id+";userName:"+userName+";course="+course+";score="+score;
    }
}
