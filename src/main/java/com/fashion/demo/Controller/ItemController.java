package com.fashion.demo.Controller;

import com.fashion.demo.Enum.ItemCategory;
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

    @GetMapping(value = "/view/category" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewItemsByCategory(@RequestParam String cate) {

        List<ItemDTO> itemDTOS =  itemService.viewItemsByCategory(cate);
        return new ResponseEntity((itemDTOS), HttpStatus.OK);
    }
}
