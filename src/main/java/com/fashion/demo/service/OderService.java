package com.fashion.demo.service;

import com.fashion.demo.dto.Order.OrderDTO;

public interface OderService {
    void createNewOrder(long user_id);

    void addItemsToOrder(long user_id);

    OrderDTO getPendingOrder(long user_id);
}
