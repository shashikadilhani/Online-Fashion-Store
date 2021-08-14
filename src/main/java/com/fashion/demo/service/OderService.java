package com.fashion.demo.service;

import com.fashion.demo.dto.Order.OrderDTO;
import com.fashion.demo.dto.item.OrderItemsDTO;
import com.fashion.demo.dto.item.UpdateItemDTO;

public interface OderService {
    void createNewOrder(long user_id);

    void addItemsToPendingOrder(long user_id, UpdateItemDTO updateItemDTO);

    OrderDTO getPendingOrder(long user_id);
}
