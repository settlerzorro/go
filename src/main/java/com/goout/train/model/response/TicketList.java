package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class TicketList {
    protected List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketList(List<Ticket> tickets) {
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
