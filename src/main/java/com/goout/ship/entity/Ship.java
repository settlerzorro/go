package com.goout.ship.entity;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.goout.bus.entity.CommentBus;
import com.goout.bus.entity.LikeBus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class Ship implements Serializable {

    private Integer id;

    /**
     * 船名
     */
    private String shipName;

    /**
     * 出发时间
     */
    private String startTime;

    /**
     * 登船港
     */
    private String fromStation;

    /**
     * 到达时间
     */
    private String endTime;

    /**
     * 抵达港
     */
    private String toStation;

    /**
     * 特等价格
     */
    private BigDecimal tdPrice;

    /**
     * 一等价格
     */
    private BigDecimal ydPrice;

    /**
     * 2等A价格
     */
    private BigDecimal edAprice;

    /**
     * 2等B价格
     */
    private BigDecimal edBprice;

    /**
     * 3等A价格
     */
    private BigDecimal sdAPrice;

    /**
     * 3等B价格
     */
    private BigDecimal sdBPrice;

    /**
     * 4等价格
     */
    private BigDecimal sdPrice;

    /**
     * 散席价格
     */
    private BigDecimal sxPrice;

    private String buyUrl;

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    private List<CommentShip> commentShips;
    private List<LikeShip> LikeShips;

    public List<CommentShip> getCommentShips() {
        return commentShips;
    }

    public void setCommentShips(List<CommentShip> commentShips) {
        this.commentShips = commentShips;
    }

    public List<LikeShip> getLikeShips() {
        return LikeShips;
    }

    public void setLikeShips(List<LikeShip> likeShips) {
        LikeShips = likeShips;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public BigDecimal getTdPrice() {
        return tdPrice;
    }

    public void setTdPrice(BigDecimal tdPrice) {
        this.tdPrice = tdPrice;
    }

    public BigDecimal getYdPrice() {
        return ydPrice;
    }

    public void setYdPrice(BigDecimal ydPrice) {
        this.ydPrice = ydPrice;
    }

    public BigDecimal getEdAprice() {
        return edAprice;
    }

    public void setEdAprice(BigDecimal edAprice) {
        this.edAprice = edAprice;
    }

    public BigDecimal getEdBprice() {
        return edBprice;
    }

    public void setEdBprice(BigDecimal edBprice) {
        this.edBprice = edBprice;
    }

    public BigDecimal getSdAPrice() {
        return sdAPrice;
    }

    public void setSdAPrice(BigDecimal sdAPrice) {
        this.sdAPrice = sdAPrice;
    }

    public BigDecimal getSdBPrice() {
        return sdBPrice;
    }

    public void setSdBPrice(BigDecimal sdBPrice) {
        this.sdBPrice = sdBPrice;
    }

    public BigDecimal getSdPrice() {
        return sdPrice;
    }

    public void setSdPrice(BigDecimal sdPrice) {
        this.sdPrice = sdPrice;
    }

    public BigDecimal getSxPrice() {
        return sxPrice;
    }

    public void setSxPrice(BigDecimal sxPrice) {
        this.sxPrice = sxPrice;
    }
}
