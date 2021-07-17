package com.shoppingcart.demo.Entity;

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

    @OneToMany(mappedBy = "oderEntity", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderItemCountEntity> orderItemCountEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private float totalPrice;

    public OderEntity() {
    }

    public OderEntity(long order_id, Date orderedDate, List<OrderItemCountEntity> orderItemCountEntities, float totalPrice) {
        this.order_id = order_id;
        this.orderedDate = orderedDate;
        this.orderItemCountEntities = orderItemCountEntities;
        this.totalPrice = totalPrice;
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
}
