package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class TicketList {
    protected List<Train> tickets;

    public List<Train> getTickets() {
        return tickets;
    }

    public void setTickets(List<Train> tickets) {
        this.tickets = tickets;
    }

    public TicketList(List<Train> tickets) {
        this.tickets = tickets;
    }

    public TicketList() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("tickets", tickets)
                .toString();
    }
}
