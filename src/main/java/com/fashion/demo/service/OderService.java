package com.fashion.demo.service;

public interface OderService {
    void createNewOrder(long user_id);

    void addItemsToOrder(long user_id);
}
