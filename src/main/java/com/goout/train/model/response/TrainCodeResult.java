package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

import java.util.Map;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class TrainCodeResult extends BaseResult {
    private Map<String, Object> result;

    public TrainCodeResult() {
    }

    public TrainCodeResult(Map<String, Object> result) {
        this.result = result;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("result", result)
                .toString();
    }
}
