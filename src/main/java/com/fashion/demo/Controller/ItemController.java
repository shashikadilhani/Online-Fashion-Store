package com.fashion.demo.Controller;

import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.dto.item.DistinctItemDTO;
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
    @GetMapping(value = "/view/catogery/types" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewItemTypesByCategory(@RequestParam String cate) {

        List<ItemType> itemTypes =  itemService.viewItemTypesByCategory(cate);
        return new ResponseEntity((itemTypes), HttpStatus.OK);
    }

    //get all items by item type and category
    @GetMapping(value = "/view/type" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewItemsByTypeandCategory(@RequestParam String type, @RequestParam String category) {

        List<DistinctItemDTO> itemDTOS =  itemService.viewItemsByType(type, category);
        return new ResponseEntity((itemDTOS), HttpStatus.OK);
    }

    //getItemBySerialNo
    @GetMapping(value = "/view/serial" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewItemsBySerial(@RequestParam int serial_no) {

        List<ItemDTO> itemDTOS =  itemService.viewItemsBySerialNo(serial_no);
        return new ResponseEntity((itemDTOS), HttpStatus.OK);
    }
}
