package com.goout.bus.entity;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class Bus implements Serializable {

    private Integer id;

    /**
     * 出发站
     */
    private String dptStation;

    /**
     * 到达站
     */
    private String arrStation;

    /**
     * 出发日
     */
    private String dptDate;

    /**
     * 出发时间
     */
    private String dptTime;

    /**
     * 车型
     */
    private String coachType;

    /**
     * 剩余票数
     */
    private String ticketLeft;

    /**
     * 票价
     */
    private BigDecimal ticketPrice;

    private List<CommentBus> commentBuses;
    private List<LikeBus> LikeBuses;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArrStation() {
        return arrStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public String getDptStation() {
        return dptStation;
    }

    public void setDptStation(String dptStation) {
        this.dptStation = dptStation;
    }

    public String getDptDate() {
        return dptDate;
    }

    public void setDptDate(String dptDate) {
        this.dptDate = dptDate;
    }

    public String getDptTime() {
        return dptTime;
    }

    public void setDptTime(String dptTime) {
        this.dptTime = dptTime;
    }

    public String getTicketLeft() {
        return ticketLeft;
    }

    public void setTicketLeft(String ticketLeft) {
        this.ticketLeft = ticketLeft;
    }


    public List<CommentBus> getCommentBuses() {
        return commentBuses;
    }

    public void setCommentBuses(List<CommentBus> commentBuses) {
        this.commentBuses = commentBuses;
    }

    public List<LikeBus> getLikeBuses() {
        return LikeBuses;
    }

    public void setLikeBuses(List<LikeBus> likeBuses) {
        LikeBuses = likeBuses;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
