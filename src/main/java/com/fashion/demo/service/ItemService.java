package com.fashion.demo.service;

import com.fashion.demo.Enum.ItemCategory;
import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.item.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(AddItemsReqDTO addItemsReqDTO);

    List<ItemDTO> viewItemsByCategory(ItemCategory category);
}
