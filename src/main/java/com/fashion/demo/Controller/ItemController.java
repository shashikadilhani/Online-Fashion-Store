package com.fashion.demo.Controller;

import com.fashion.demo.Enum.ItemCategory;
import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.dto.item.ItemDTO;
import com.fashion.demo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //get distinct item types based on category
    @GetMapping(value = "/view/catogery" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewItemTypesByCategory(@RequestParam String cate) {

        List<ItemType> itemTypes =  itemService.viewItemTypesByCategory(cate);
        return new ResponseEntity((itemTypes), HttpStatus.OK);
    }

    //get all items by item type
//    @GetMapping(value = "/view/category/types" ,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity viewItemsByTypeandCategory(@RequestParam String cate) {
//
//        List<ItemDTO> itemDTOS =  itemService.viewItemsByCategory(cate);
//        return new ResponseEntity((itemDTOS), HttpStatus.OK);
//    }
}
