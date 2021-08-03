package com.fashion.demo.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_order_item_count")
public class OrderItemCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_item_id;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "tbl_order_item", joinColumns = {
            @JoinColumn(name = "order_item_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private List<ItemEntity> items = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_id")
    private OderEntity oderEntity;

    private int item_count;

    public OrderItemCountEntity() {
    }

    public OrderItemCountEntity(long order_item_id, ItemEntity itemEntity, OderEntity oderEntity, int item_count) {
        this.order_item_id = order_item_id;
//        this.itemEntity = itemEntity;
        this.oderEntity = oderEntity;
        this.item_count = item_count;
    }

    public long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(long order_item_id) {
        this.order_item_id = order_item_id;
    }

//    public ItemEntity getItemEntity() {
//        return itemEntity;
//    }
//
//    public void setItemEntity(ItemEntity itemEntity) {
//        this.itemEntity = itemEntity;
//    }

    public OderEntity getOderEntity() {
        return oderEntity;
    }

    public void setOderEntity(OderEntity oderEntity) {
        this.oderEntity = oderEntity;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
}
