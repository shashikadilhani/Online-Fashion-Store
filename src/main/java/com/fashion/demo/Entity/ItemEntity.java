package com.fashion.demo.Entity;

import com.fashion.demo.Enum.ItemCategory;
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

    private long item_serial_no;

    private String item_name;

    @Enumerated(EnumType.STRING)
    @Column(name ="item_category")
    private ItemCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name ="item_type")
    private ItemType type;

    @Enumerated(EnumType.STRING)
    @Column(name ="size")
    private SizeType size;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

    private float price;

    @Column(length = 5000)
    private String image;

    public ItemEntity() {
    }

    public ItemEntity(long item_id, long item_serial_no, String item_name, ItemCategory category, ItemType type, SizeType size, Date addedDate, float price, String image) {
        this.item_id = item_id;
        this.item_serial_no = item_serial_no;
        this.item_name = item_name;
        this.category = category;
        this.type = type;
        this.size = size;
        this.addedDate = addedDate;
        this.price = price;
        this.image = image;
    }

    public long getItem_serial_no() {
        return item_serial_no;
    }

    public void setItem_serial_no(long item_serial_no) {
        this.item_serial_no = item_serial_no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }


}
