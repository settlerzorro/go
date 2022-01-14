package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class TicketListResult extends BaseResult {
    private TicketList result;

    public TicketListResult() {
    }

    public TicketListResult(TicketList result) {
        this.result = result;
    }

    public TicketList getResult() {
        return result;
    }

    public void setResult(TicketList result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("result", result)
                .toString();
    }
}
