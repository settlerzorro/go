package com.goout.advert.entity;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class Advert implements Serializable {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    private Integer id;

    private String name;

    private String localUrl;

    private Object img;

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }
}
