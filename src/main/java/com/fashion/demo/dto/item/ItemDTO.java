package com.fashion.demo.dto.item;

import com.fashion.demo.Enum.ItemCategory;
import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.Enum.SizeType;

public class ItemDTO {
    private long id;
    private String item_name;
    private ItemCategory category;
    private ItemType type;
    private SizeType size;
    private float price;
    private String image;
    private int serial_no;

    public ItemDTO() {
    }

    public ItemDTO(String item_name, String image, int serial_no) {
        this.item_name = item_name;
        this.image = image;
        this.serial_no = serial_no;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(int serial_no) {
        this.serial_no = serial_no;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", size=" + size +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", serial_no=" + serial_no +
                '}';
    }
}
