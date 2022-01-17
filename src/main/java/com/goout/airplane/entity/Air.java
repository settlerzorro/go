package com.goout.airplane.entity;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class Air implements Serializable {

    private Integer id;

    /**
     * 出发站
     */
    private String oapName;

    /**
     * 到达站
     */
    private String aapName;

    /**
     * 出发时刻
     */
    private String flyOffOnlyTime;

    /**
     * 到达时刻
     */
    private String arrivalOnlyTime;

    /**
     * 票价
     */
    private BigDecimal aep;

    /**
     * 出发日
     */
    private String fromTime;

    /**
     * 历时
     */
    private Integer useTime;

    /**
     * 经停
     */
    private String si;

    /**
     * 购票地址
     */
    private String buyUrl;

    private List<CommentAir> commentAairs;
    private List<LikeAir> LikeAirs;

    public List<CommentAir> getCommentAairs() {
        return commentAairs;
    }

    public void setCommentAairs(List<CommentAir> commentAairs) {
        this.commentAairs = commentAairs;
    }

    public List<LikeAir> getLikeAirs() {
        return LikeAirs;
    }

    public void setLikeAirs(List<LikeAir> likeAirs) {
        LikeAirs = likeAirs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOapName() {
        return oapName;
    }

    public void setOapName(String oapName) {
        this.oapName = oapName;
    }

    public String getAapName() {
        return aapName;
    }

    public void setAapName(String aapName) {
        this.aapName = aapName;
    }

    public String getFlyOffOnlyTime() {
        return flyOffOnlyTime;
    }

    public void setFlyOffOnlyTime(String flyOffOnlyTime) {
        this.flyOffOnlyTime = flyOffOnlyTime;
    }

    public String getArrivalOnlyTime() {
        return arrivalOnlyTime;
    }

    public void setArrivalOnlyTime(String arrivalOnlyTime) {
        this.arrivalOnlyTime = arrivalOnlyTime;
    }

    public BigDecimal getAep() {
        return aep;
    }

    public void setAep(BigDecimal aep) {
        this.aep = aep;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }
}
