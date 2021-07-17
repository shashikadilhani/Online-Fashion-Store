package com.shoppingcart.demo.Controller;

import com.shoppingcart.demo.dto.Order.AddItemsReqDTO;
import com.shoppingcart.demo.dto.request.OrderRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("customer")
public class CustomerController {

    @PostMapping(value = "/addItems/{user_id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItemsToOrder(@RequestBody AddItemsReqDTO addItemsReqDTO) {

//        EventCategoryEntity category = eventCategoryService.findCategoryByEventCategoryId(event_category_id);

//        return new ResponseEntity(new CategoryListResponseDTO(true, category), HttpStatus.OK);
        return null;
    }

}
