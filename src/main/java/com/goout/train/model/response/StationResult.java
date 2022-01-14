package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class StationResult extends BaseResult {
    private StationList result;

    public StationResult() {
    }

    public StationResult(StationList result) {
        this.result = result;
    }

    public StationList getResult() {
        return result;
    }

    public void setResult(StationList result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("result", result)
                .toString();
    }
}
