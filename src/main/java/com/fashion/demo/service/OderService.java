package com.fashion.demo.service;

import com.fashion.demo.dto.Order.OderItemCountDTO;
import com.fashion.demo.dto.Order.OrderDTO;
import com.fashion.demo.dto.item.OrderItemsDTO;
import com.fashion.demo.dto.item.UpdateItemDTO;

import java.util.List;

public interface OderService {
    void createNewOrder(long user_id);

    void addItemsToPendingOrder(long user_id, UpdateItemDTO updateItemDTO);

    OrderDTO getPendingOrder(long user_id);

    List<OrderDTO> findUserOrders(long user_id);

    List<OderItemCountDTO> getOderDetail(long order_id);
}
