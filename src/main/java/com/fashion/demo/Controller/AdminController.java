package com.fashion.demo.Controller;

import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.response.CommonResponseDTO;
import com.fashion.demo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {


    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(value = "/addItems",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItemsToOrder(@RequestBody AddItemsReqDTO addItemsReqDTO) {

        itemService.addItem(addItemsReqDTO);
        return new ResponseEntity(new CommonResponseDTO(true, "ItemAddedSuccessfully"), HttpStatus.OK);
    }
}
