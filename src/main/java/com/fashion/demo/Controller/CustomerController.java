package com.fashion.demo.Controller;

import com.fashion.demo.Entity.UserSummary;
import com.fashion.demo.Security.CurrentUser;
import com.fashion.demo.Security.UserPrincipal;
import com.fashion.demo.dto.Order.OrderDTO;
import com.fashion.demo.dto.user.UserViewDTO;
import com.fashion.demo.service.OderService;
import com.fashion.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("customer")
public class CustomerController {

    private final UserService userService;
    private final OderService oderService;

    public CustomerController(UserService userService, OderService oderService) {
        this.userService = userService;
        this.oderService = oderService;
    }

    @GetMapping(value = "/view/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewUserById(@PathVariable long user_id) {

        UserViewDTO userViewDTO =  userService.viewUser(user_id);
        return new ResponseEntity(userViewDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/orders/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewOrders(@PathVariable long user_id) {

        List<OrderDTO> orderDTOS = oderService.findUserOrders(user_id);
        return new ResponseEntity(orderDTOS, HttpStatus.OK);
    }

    // update user profile
    @PatchMapping(value = "/update/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProfile(@PathVariable long user_id, @RequestBody UserViewDTO userViewDTO) {

        userService.updateProfile(user_id,userViewDTO);
        return new ResponseEntity("profile Updated Successfully! Please Refresh", HttpStatus.OK);
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }
}
