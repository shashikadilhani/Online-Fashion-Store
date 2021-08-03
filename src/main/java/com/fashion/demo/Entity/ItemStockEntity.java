package com.fashion.demo.Entity;

import com.fashion.demo.Enum.ItemCategory;
import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.Enum.SizeType;
import com.fashion.demo.Enum.StockStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_item_stock")
public class ItemStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long item_stock_id;

    @Enumerated(EnumType.STRING)
    @Column(name ="stock_type")
    private StockStatus stock_type;

    private int item_stock_count;

    @OneToOne
    @JoinColumn(name = "item_id", unique = true)
    private ItemEntity itemEntity;

    public ItemStockEntity() {
    }

    public long getItem_stock_id() {
        return item_stock_id;
    }

    public void setItem_stock_id(long item_stock_id) {
        this.item_stock_id = item_stock_id;
    }

    public StockStatus getStock_type() {
        return stock_type;
    }

    public void setStock_type(StockStatus stock_type) {
        this.stock_type = stock_type;
    }

    public int getItem_stock_count() {
        return item_stock_count;
    }

    public void setItem_stock_count(int item_stock_count) {
        this.item_stock_count = item_stock_count;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }
}
