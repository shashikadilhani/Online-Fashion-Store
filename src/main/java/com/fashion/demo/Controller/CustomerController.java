package com.fashion.demo.Controller;

import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.response.CommonDataResponseDTO;
import com.fashion.demo.dto.response.CommonResponseDTO;
import com.fashion.demo.dto.user.UserDTO;
import com.fashion.demo.dto.user.UserListDTO;
import com.fashion.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("customer")
public class CustomerController {

    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addItems/{user_id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItemsToOrder(@RequestBody AddItemsReqDTO addItemsReqDTO) {

//        EventCategoryEntity category = eventCategoryService.findCategoryByEventCategoryId(event_category_id);

//        return new ResponseEntity(new CategoryListResponseDTO(true, category), HttpStatus.OK);
        return null;
    }
}
