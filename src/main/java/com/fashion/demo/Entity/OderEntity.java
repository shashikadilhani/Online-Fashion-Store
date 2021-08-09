package com.fashion.demo.Entity;

import com.fashion.demo.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tbl_order")
public class OderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private float totalPrice;

    @Column(name= "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OderEntity() {
    }

    public OderEntity(long order_id, Date orderedDate, UserEntity userEntity, float totalPrice, OrderStatus oderStatus) {
        this.order_id = order_id;
        this.orderedDate = orderedDate;
        this.userEntity = userEntity;
        this.totalPrice = totalPrice;
        this.orderStatus = oderStatus;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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
}
