package com.fashion.demo.Controller;

import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.response.CommonResponseDTO;
import com.fashion.demo.dto.user.UserDTO;
import com.fashion.demo.service.ItemService;
import com.fashion.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("admin")
public class AdminController {


    private final ItemService itemService;
    private final UserService userService;

    public AdminController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @PostMapping(value = "/addItems",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItemsToOrder(@RequestBody AddItemsReqDTO addItemsReqDTO) {

        itemService.addItem(addItemsReqDTO);
        return new ResponseEntity(new CommonResponseDTO(true, "ItemAddedSuccessfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewAll() {

        List<UserDTO> userListDTO =  userService.getAllUsers();
        return new ResponseEntity((userListDTO), HttpStatus.OK);
    }
}
