package com.fashion.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_order_item_count")
public class OrderItemCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_item_id;

    @OneToOne(mappedBy = "orderItemCountEntity", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ItemEntity itemEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OderEntity oderEntity;

    private int item_count;

    public OrderItemCountEntity() {
    }

    public OrderItemCountEntity(long order_item_id, ItemEntity itemEntity, OderEntity oderEntity, int item_count) {
        this.order_item_id = order_item_id;
        this.itemEntity = itemEntity;
        this.oderEntity = oderEntity;
        this.item_count = item_count;
    }

    public long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(long order_item_id) {
        this.order_item_id = order_item_id;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

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
