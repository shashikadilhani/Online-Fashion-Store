package com.fashion.demo.dto.item;
import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.Enum.SizeType;


public class AddItemsReqDTO {
    private long item_id;
    private String item_name;
    private ItemType category;
    private SizeType size;
    private float price;

    public AddItemsReqDTO() {
    }

    public AddItemsReqDTO(long item_id, String item_name, ItemType category, SizeType size, float price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.category = category;
        this.size = size;
        this.price = price;
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

    public ItemType getCategory() {
        return category;
    }

    public void setCategory(ItemType category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "AddItemsReqDTO{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", category=" + category +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
