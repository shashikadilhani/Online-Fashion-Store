package com.fashion.demo.dto.Order;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Entity.OderEntity;
import com.fashion.demo.dto.item.ItemDTO;

public class OderItemCountDTO {

    private long order_item_count_id;
    private long order_id;
    private ItemDTO item;
    private int item_count;

    public OderItemCountDTO() {
    }

    public OderItemCountDTO(long order_item_count_id, long order_id, ItemDTO item, int item_count) {
        this.order_item_count_id = order_item_count_id;
        this.order_id = order_id;
        this.item = item;
        this.item_count = item_count;
    }

    public long getOrder_item_count_id() {
        return order_item_count_id;
    }

    public void setOrder_item_count_id(long order_item_count_id) {
        this.order_item_count_id = order_item_count_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    @Override
    public String toString() {
        return "OderItemCountDTO{" +
                "order_item_count_id=" + order_item_count_id +
                ", order_id=" + order_id +
                ", item=" + item +
                ", item_count=" + item_count +
                '}';
    }
}
