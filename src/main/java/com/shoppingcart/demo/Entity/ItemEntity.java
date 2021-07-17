package com.shoppingcart.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.demo.Enum.ItemType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long item_id;

    private String itemname;

    @Enumerated(EnumType.STRING)
    @Column(name ="tbl_item_category")
    private ItemType type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date manuDate;

    private float price;

    //remaining stock
    private float item_stock;

    @OneToOne
    @JoinColumn(name = "order_item_id", unique = true)
    private OrderItemCountEntity orderItemCountEntity;


    public ItemEntity() {
    }

    public ItemEntity(long item_id, String itemname, ItemType type, Date expiryDate, Date manuDate, float price, float item_stock, OrderItemCountEntity orderItemCountEntity) {
        this.item_id = item_id;
        this.itemname = itemname;
        this.type = type;
        this.expiryDate = expiryDate;
        this.manuDate = manuDate;
        this.price = price;
        this.item_stock = item_stock;
        this.orderItemCountEntity = orderItemCountEntity;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getManuDate() {
        return manuDate;
    }

    public void setManuDate(Date manuDate) {
        this.manuDate = manuDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getItem_stock() {
        return item_stock;
    }

    public void setItem_stock(float item_stock) {
        this.item_stock = item_stock;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public OrderItemCountEntity getOrderItemCountEntity() {
        return orderItemCountEntity;
    }

    public void setOrderItemCountEntity(OrderItemCountEntity orderItemCountEntity) {
        this.orderItemCountEntity = orderItemCountEntity;
    }
}
