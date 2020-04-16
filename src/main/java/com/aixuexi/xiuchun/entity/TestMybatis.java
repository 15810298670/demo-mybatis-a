package com.aixuexi.xiuchun.entity;

import java.io.Serializable;

/**
 * create on 20/4/1 下午1:10 by jc
 **/
public class TestMybatis implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}