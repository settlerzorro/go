package com.goout.train.model.request;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;
import com.goout.train.exception.ServiceException;
import com.goout.train.enums.BusinessErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.Date;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class GetTicketListRequest {
    /**
     * 出发站点代码
     */
    private String fromStation;

    /**
     * 到达站点代码
     */
    private String toStation;

    /**
     * 出发日期（格式：yyyy-mm-dd）
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date fromDate;


    public void validate() {
        if (StringUtils.isBlank(fromStation)) {
            // || StringUtils.isBlank(toStation) || fromDate == null
            throw new ServiceException(BusinessErrorCode.REQUEST_PARAM_MISS);
        }

        if (new DateTime(fromDate).plusDays(1).minusSeconds(1).isBeforeNow()) {
            throw new ServiceException(BusinessErrorCode.REQUEST_PARAM_ERROR, "出发时间必须大于当前时间");
        }
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fromStationCode", fromStation)
                .add("toStationCode", toStation)
                .add("fromDate", fromDate)
                .toString();
    }
}
