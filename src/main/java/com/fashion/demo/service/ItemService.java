package com.fashion.demo.service;

import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.item.DistinctItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(AddItemsReqDTO addItemsReqDTO);

    List<ItemType> viewItemTypesByCategory(String category);

    List<DistinctItemDTO> viewItemsByType(String type, String category);
}
