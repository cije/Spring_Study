package com.ce.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class User1 implements Serializable {

    private String uname;
    private Integer age;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User1{" +
                "uname='" + uname + '\'' +
                ", age='" + age + '\'' +
                ", date=" + date +
                '}';
    }
}
