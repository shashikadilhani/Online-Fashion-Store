package com.fashion.demo.dto.Order;

import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

public class OrderDTO {

    private long order_id;
    private Date orderedDate;
    private long user_id;
    private float totalPrice;
    private OrderStatus orderStatus;

    public OrderDTO() {
    }

    public OrderDTO(long order_id, Date orderedDate, long user_id, float totalPrice, OrderStatus orderStatus) {
        this.order_id = order_id;
        this.orderedDate = orderedDate;
        this.user_id = user_id;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "order_id=" + order_id +
                ", orderedDate=" + orderedDate +
                ", user_id=" + user_id +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
