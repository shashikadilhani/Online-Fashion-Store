package com.fashion.demo.service;

import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.dto.user.UserDTO;
import com.fashion.demo.dto.user.UserListDTO;
import com.fashion.demo.dto.user.UserViewDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserEntity findUserForValidation(String name);

    UserViewDTO viewUser(long user_id);
}
