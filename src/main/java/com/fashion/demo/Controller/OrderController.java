package com.fashion.demo.Controller;

import com.fashion.demo.dto.Order.OderItemCountDTO;
import com.fashion.demo.dto.Order.OrderDTO;
import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.item.OrderItemsDTO;
import com.fashion.demo.dto.item.UpdateItemDTO;
import com.fashion.demo.dto.response.CommonResponseDTO;
import com.fashion.demo.service.OderService;
import com.fashion.demo.util.UserControllerAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("order")
public class OrderController {

    private final UserControllerAuthenticator userControllerAuthenticator;
    private final OderService oderService;

    public OrderController(UserControllerAuthenticator userControllerAuthenticator, OderService oderService) {
        this.userControllerAuthenticator = userControllerAuthenticator;
        this.oderService = oderService;
    }

    //Start Order
    @PostMapping(value = "/start/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity startOrder(@PathVariable long user_id) {

        oderService.createNewOrder(user_id);
        return new ResponseEntity("successfully created", HttpStatus.OK);
    }
    //Start Order
    @GetMapping(value = "/pending/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPendingOrder(@PathVariable long user_id) {

        OrderDTO orderDTO = oderService.getPendingOrder(user_id);
        return new ResponseEntity(orderDTO , HttpStatus.OK);
    }

    //update pending order by adding item
    @PatchMapping(value = "/additems/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItemsToPendingOrder(@PathVariable long user_id, @RequestBody UpdateItemDTO updateItemDTO) {

        oderService.addItemsToPendingOrder(user_id,updateItemDTO);
        return new ResponseEntity("successfully Added Items", HttpStatus.OK);
    }

    //view order by order id
    @GetMapping(value = "/{order_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOderDetails(@PathVariable long order_id) {

        List<OderItemCountDTO> order = oderService.getOderDetail(order_id);
        return new ResponseEntity(order , HttpStatus.OK);
    }

}
