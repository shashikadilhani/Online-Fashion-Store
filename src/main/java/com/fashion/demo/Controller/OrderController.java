package com.fashion.demo.Controller;

import com.fashion.demo.service.OderService;
import com.fashion.demo.util.UserControllerAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/start/user_id" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity startOrder(@RequestParam long user_id) {

        userControllerAuthenticator.validateUser(user_id);
        oderService.createNewOrder(user_id);
        return new ResponseEntity("successfully created", HttpStatus.OK);
    }
}
