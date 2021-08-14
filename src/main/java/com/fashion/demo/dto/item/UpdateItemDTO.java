package com.fashion.demo.dto.item;

public class UpdateItemDTO {
    private long item_id;
    private float price;

    public UpdateItemDTO() {
    }

    public UpdateItemDTO(long item_id, float price) {
        this.item_id = item_id;
        this.price = price;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UpdateItemDTO{" +
                "item_id=" + item_id +
                ", price=" + price +
                '}';
    }
}
