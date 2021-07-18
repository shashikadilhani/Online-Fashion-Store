package com.fashion.demo.Entity;

import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.Enum.SizeType;
import com.fashion.demo.Enum.StockStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long item_id;

    private String item_name;

    @Enumerated(EnumType.STRING)
    @Column(name ="item_category")
    private ItemType category;

    @Enumerated(EnumType.STRING)
    @Column(name ="size")
    private SizeType size;

    @Enumerated(EnumType.STRING)
    @Column(name ="stock")
    private StockStatus stock;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    private float price;
    private float item_stock;

    @OneToOne
    @JoinColumn(name = "order_item_id", unique = true)
    private OrderItemCountEntity orderItemCountEntity;


    public ItemEntity() {
    }

    public ItemEntity(long item_id, String item_name, ItemType category, SizeType size, StockStatus stock, Date addedDate, float price, float item_stock, OrderItemCountEntity orderItemCountEntity) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.category = category;
        this.size = size;
        this.stock = stock;
        this.addedDate = addedDate;
        this.price = price;
        this.item_stock = item_stock;
        this.orderItemCountEntity = orderItemCountEntity;
    }

    public StockStatus getStock() {
        return stock;
    }

    public void setStock(StockStatus stock) {
        this.stock = stock;
    }

    public SizeType getSize() {
        return size;
    }

    public void setSize(SizeType size) {
        this.size = size;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public ItemType getCategory() {
        return category;
    }

    public void setCategory(ItemType category) {
        this.category = category;
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

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public OrderItemCountEntity getOrderItemCountEntity() {
        return orderItemCountEntity;
    }

    public void setOrderItemCountEntity(OrderItemCountEntity orderItemCountEntity) {
        this.orderItemCountEntity = orderItemCountEntity;
    }
}
