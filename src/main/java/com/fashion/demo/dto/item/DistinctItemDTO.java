package com.fashion.demo.dto.item;

public class DistinctItemDTO {

    private String id;
    private String item_name;
    private String image;
    private int serial_no;

    public DistinctItemDTO() {
    }

    public DistinctItemDTO(String id, String item_name, String image, int serial_no) {
        this.id = id;
        this.item_name = item_name;
        this.image = image;
        this.serial_no = serial_no;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DistinctItemDTO{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", image='" + image + '\'' +
                ", serial_no=" + serial_no +
                '}';
    }
}
