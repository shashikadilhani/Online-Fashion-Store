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
    private StockStatus stock_type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    private float price;
    private int item_stock;

    @OneToOne
    @JoinColumn(name = "order_item_id", unique = true)
    private OrderItemCountEntity orderItemCountEntity;

    private String image;

    public ItemEntity() {
    }

    public ItemEntity(long item_id, String item_name, ItemType category, SizeType size, StockStatus stock_type, Date addedDate, float price, int item_stock, OrderItemCountEntity orderItemCountEntity, String image) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.category = category;
        this.size = size;
        this.stock_type = stock_type;
        this.addedDate = addedDate;
        this.price = price;
        this.item_stock = item_stock;
        this.orderItemCountEntity = orderItemCountEntity;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public StockStatus getStock_type() {
        return stock_type;
    }

    public void setStock_type(StockStatus stock_type) {
        this.stock_type = stock_type;
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

    public void setItem_stock(int item_stock) {
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
