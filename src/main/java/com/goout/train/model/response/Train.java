package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class Train implements Serializable {

    private Integer id;
    /**
     * 列车号
     */
    private String trainNo;

    /**
     * 车次代码
     */
    private String trainCode;

    /**
     * 车次类型
     */
    private String trainType;

    /**
     * 出发站点名字
     */
    private String fromStation;

    /**
     * 到达站点名字
     */
    private String toStation;

    /**
     * 出发站点类型名 "始|终|过"类型
     */
    private String fromStationType;

    /**
     * 到达站点类型名 "始|终|过"类型
     */
    private String toStationType;

    /**
     * 出发时间
     */
    private String fromTime;

    /**
     * 到达时间
     */
    private String toTime;

    /**
     * 运行时间
     */
    private String runTime;

    /**
     * 该车次是否有余票可以预定，所有席别无票则为false
     */
    private Boolean canBook;

    /**
     * 	商务座/特等座剩余票数
     * 	<p>可能值
     * 	有|表示充足
     * 	--|表示无此类型的座位
     * 	数字表示剩余座位数 )
     * 	</p>
     * 	<p>下同</p>
     */
    private String swzNum;

    /**
     * 商务座/特等座价格
     */
    private BigDecimal swzPrice;

    /**
     * 	一等座剩余票数
     */
    private String ydzNum;

    /**
     * 一等座价格
     */
    private BigDecimal ydzPrice;

    /**
     * 	二等座剩余票数
     */
    private String edzNum;

    /**
     * 二等座价格
     */
    private BigDecimal edzPrice;

    /**
     * 	高级软卧剩余票数
     */
    private String gjrwNum;

    /**
     * 高级软卧价格
     */
    private BigDecimal gjrwPrice;

    /**
     * 	软卧/一等卧剩余票数
     */
    private String rwNum;

    /**
     *  软卧/一等卧价格
     */
    private BigDecimal rwPrice;

    /**
     * 	动卧剩余票数
     */
    private String dwNum;

    /**
     *  动卧价格
     */
    private BigDecimal dwPrice;

    /**
     * 	硬卧剩余票数
     */
    private String ywNum;

    /**
     * 硬卧价格
     */
    private BigDecimal ywPrice;

    /**
     * 	软座剩余票数
     */
    private String rzNum;

    /**
     * 软座价格
     */
    private BigDecimal rzPrice;

    /**
     * 	硬座剩余票数
     */
    private String yzNum;

    /**
     * 硬座价格
     */
    private BigDecimal yzPrice;

    /**
     * 	无座剩余票数
     */
    private String wzNum;

    /**
     * 无座价格
     */
    private BigDecimal wzPrice;

    /**
     * 	其他剩余票数
     */
    private String qtNum;

    /**
     * 其他价格
     */
    private BigDecimal qtPrice;

    /**
     * 车次经停信息
     */
    private String trainLines;

    /**
     * 评论
     */
    private List<CommentTrain> commentTrains;

    /**
     * 收藏
     */
    private List<LikeTrain> likes;

    /**
     * 发车日
     */
    private String fromDate;

    private String buyUrl;

    /**
     * 目的地天气
     */
    private String weather;

    /**
     * 目的地景点
     */
    private String scenicSpots;

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", trainNo='" + trainNo + '\'' +
                ", trainCode='" + trainCode + '\'' +
                ", trainType='" + trainType + '\'' +
                ", fromStation='" + fromStation + '\'' +
                ", toStation='" + toStation + '\'' +
                ", fromStationType='" + fromStationType + '\'' +
                ", toStationType='" + toStationType + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                ", runTime='" + runTime + '\'' +
                ", canBook=" + canBook +
                ", swzNum='" + swzNum + '\'' +
                ", swzPrice=" + swzPrice +
                ", ydzNum='" + ydzNum + '\'' +
                ", ydzPrice=" + ydzPrice +
                ", edzNum='" + edzNum + '\'' +
                ", edzPrice=" + edzPrice +
                ", gjrwNum='" + gjrwNum + '\'' +
                ", gjrwPrice=" + gjrwPrice +
                ", rwNum='" + rwNum + '\'' +
                ", rwPrice=" + rwPrice +
                ", dwNum='" + dwNum + '\'' +
                ", dwPrice=" + dwPrice +
                ", ywNum='" + ywNum + '\'' +
                ", ywPrice=" + ywPrice +
                ", rzNum='" + rzNum + '\'' +
                ", rzPrice=" + rzPrice +
                ", yzNum='" + yzNum + '\'' +
                ", yzPrice=" + yzPrice +
                ", wzNum='" + wzNum + '\'' +
                ", wzPrice=" + wzPrice +
                ", qtNum='" + qtNum + '\'' +
                ", qtPrice=" + qtPrice +
                ", trainLines='" + trainLines + '\'' +
                ", commentTrains=" + commentTrains +
                ", likes=" + likes +
                ", fromDate='" + fromDate + '\'' +
                ", buyUrl='" + buyUrl + '\'' +
                ", weather='" + weather + '\'' +
                ", scenicSpots='" + scenicSpots + '\'' +
                '}';
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getScenicSpots() {
        return scenicSpots;
    }

    public void setScenicSpots(String scenicSpots) {
        this.scenicSpots = scenicSpots;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getFromStationType() {
        return fromStationType;
    }

    public void setFromStationType(String fromStationType) {
        this.fromStationType = fromStationType;
    }

    public String getToStationType() {
        return toStationType;
    }

    public void setToStationType(String toStationType) {
        this.toStationType = toStationType;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public Boolean getCanBook() {
        return canBook;
    }

    public void setCanBook(Boolean canBook) {
        this.canBook = canBook;
    }

    public String getSwzNum() {
        return swzNum;
    }

    public void setSwzNum(String swzNum) {
        this.swzNum = swzNum;
    }

    public BigDecimal getSwzPrice() {
        return swzPrice;
    }

    public void setSwzPrice(BigDecimal swzPrice) {
        this.swzPrice = swzPrice;
    }

    public String getYdzNum() {
        return ydzNum;
    }

    public void setYdzNum(String ydzNum) {
        this.ydzNum = ydzNum;
    }

    public BigDecimal getYdzPrice() {
        return ydzPrice;
    }

    public void setYdzPrice(BigDecimal ydzPrice) {
        this.ydzPrice = ydzPrice;
    }

    public String getEdzNum() {
        return edzNum;
    }

    public void setEdzNum(String edzNum) {
        this.edzNum = edzNum;
    }

    public BigDecimal getEdzPrice() {
        return edzPrice;
    }

    public void setEdzPrice(BigDecimal edzPrice) {
        this.edzPrice = edzPrice;
    }

    public String getGjrwNum() {
        return gjrwNum;
    }

    public void setGjrwNum(String gjrwNum) {
        this.gjrwNum = gjrwNum;
    }

    public BigDecimal getGjrwPrice() {
        return gjrwPrice;
    }

    public void setGjrwPrice(BigDecimal gjrwPrice) {
        this.gjrwPrice = gjrwPrice;
    }

    public String getRwNum() {
        return rwNum;
    }

    public void setRwNum(String rwNum) {
        this.rwNum = rwNum;
    }

    public BigDecimal getRwPrice() {
        return rwPrice;
    }

    public void setRwPrice(BigDecimal rwPrice) {
        this.rwPrice = rwPrice;
    }

    public String getDwNum() {
        return dwNum;
    }

    public void setDwNum(String dwNum) {
        this.dwNum = dwNum;
    }

    public BigDecimal getDwPrice() {
        return dwPrice;
    }

    public void setDwPrice(BigDecimal dwPrice) {
        this.dwPrice = dwPrice;
    }

    public String getYwNum() {
        return ywNum;
    }

    public void setYwNum(String ywNum) {
        this.ywNum = ywNum;
    }

    public BigDecimal getYwPrice() {
        return ywPrice;
    }

    public void setYwPrice(BigDecimal ywPrice) {
        this.ywPrice = ywPrice;
    }

    public String getRzNum() {
        return rzNum;
    }

    public void setRzNum(String rzNum) {
        this.rzNum = rzNum;
    }

    public BigDecimal getRzPrice() {
        return rzPrice;
    }

    public void setRzPrice(BigDecimal rzPrice) {
        this.rzPrice = rzPrice;
    }

    public String getYzNum() {
        return yzNum;
    }

    public void setYzNum(String yzNum) {
        this.yzNum = yzNum;
    }

    public BigDecimal getYzPrice() {
        return yzPrice;
    }

    public void setYzPrice(BigDecimal yzPrice) {
        this.yzPrice = yzPrice;
    }

    public String getWzNum() {
        return wzNum;
    }

    public void setWzNum(String wzNum) {
        this.wzNum = wzNum;
    }

    public BigDecimal getWzPrice() {
        return wzPrice;
    }

    public void setWzPrice(BigDecimal wzPrice) {
        this.wzPrice = wzPrice;
    }

    public String getQtNum() {
        return qtNum;
    }

    public void setQtNum(String qtNum) {
        this.qtNum = qtNum;
    }

    public BigDecimal getQtPrice() {
        return qtPrice;
    }

    public void setQtPrice(BigDecimal qtPrice) {
        this.qtPrice = qtPrice;
    }

    public String getTrainLines() {
        return trainLines;
    }

    public void setTrainLines(String trainLines) {
        this.trainLines = trainLines;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CommentTrain> getCommentTrains() {
        return commentTrains;
    }

    public void setCommentTrains(List<CommentTrain> commentTrains) {
        this.commentTrains = commentTrains;
    }

    public List<LikeTrain> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeTrain> likes) {
        this.likes = likes;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
