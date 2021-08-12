package com.fashion.demo.dto.Order;

import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

public class OrderDTO {

    private long order_id;
    private Date orderedDate;
    private UserEntity userEntity;
    private float totalPrice;
    private OrderStatus orderStatus;
}
