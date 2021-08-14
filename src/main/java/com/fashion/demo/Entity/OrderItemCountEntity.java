package com.fashion.demo.Entity;
import javax.persistence.*;

@Entity
@Table(name = "tbl_order_item_count")
public class OrderItemCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_item_count_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_order_id")
    private  OderEntity oderEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_item_id")
    private ItemEntity itemEntity;

    public OrderItemCountEntity(long order_item_count_id, OderEntity oderEntity, ItemEntity itemEntity, int item_count) {
        this.order_item_count_id = order_item_count_id;
        this.oderEntity = oderEntity;
        this.itemEntity = itemEntity;
        this.item_count = item_count;
    }

    public long getOrder_item_count_id() {
        return order_item_count_id;
    }

    public void setOrder_item_count_id(long order_item_count_id) {
        this.order_item_count_id = order_item_count_id;
    }

    public OderEntity getOderEntity() {
        return oderEntity;
    }

    public void setOderEntity(OderEntity oderEntity) {
        this.oderEntity = oderEntity;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    private int item_count;

    public OrderItemCountEntity() {
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
}
