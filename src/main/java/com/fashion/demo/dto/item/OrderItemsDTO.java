package com.fashion.demo.dto.item;

import com.fashion.demo.Enum.ItemCategory;
import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.Enum.SizeType;

public class OrderItemsDTO {
    private long item_id;
    private String item_name;
    private ItemCategory category;
    private ItemType type;
    private SizeType size;
    private float price;
    private String image;
    private long item_serial_no;

    public OrderItemsDTO() {
    }

    public OrderItemsDTO(long item_id, String item_name, ItemCategory category, ItemType type, SizeType size, float price, String image, long item_serial_no) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.category = category;
        this.type = type;
        this.size = size;
        this.price = price;
        this.image = image;
        this.item_serial_no = item_serial_no;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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

    public SizeType getSize() {
        return size;
    }

    public void setSize(SizeType size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getItem_serial_no() {
        return item_serial_no;
    }

    public void setItem_serial_no(long item_serial_no) {
        this.item_serial_no = item_serial_no;
    }

    @Override
    public String toString() {
        return "OrderItemsDTO{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", size=" + size +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", item_serial_no=" + item_serial_no +
                '}';
    }
}
