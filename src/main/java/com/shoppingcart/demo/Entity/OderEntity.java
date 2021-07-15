package com.shoppingcart.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tbl_order")
public class OderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_order_items",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<ItemEntity> orderItems = new HashSet<>();

    private int item_type_count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity customer;

    private float totalPrice;



}
