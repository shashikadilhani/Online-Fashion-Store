package com.fashion.demo.service;

import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.item.DistinctItemDTO;
import com.fashion.demo.dto.item.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(AddItemsReqDTO addItemsReqDTO);

    List<ItemType> viewItemTypesByCategory(String category);

    List<DistinctItemDTO> viewItemsByType(String type, String category);

    List<ItemDTO> viewItemsBySerialNo(int serial_no);

    List<String> viewItemsSizes(int serial_no);
}
