package com.fashion.demo.Controller;


import com.fashion.demo.Entity.RoleEntity;
import com.fashion.demo.Enum.RoleName;
import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Exception.AppException;
import com.fashion.demo.Repository.RoleRepository;
import com.fashion.demo.Repository.UserRepository;
import com.fashion.demo.Security.JwtTokenProvider;
import com.fashion.demo.dto.request.UserLoginReqDTO;
import com.fashion.demo.dto.request.UserRegRequestDTO;
import com.fashion.demo.dto.response.CommonResponseDTO;
import com.fashion.demo.dto.response.JwtAuthenticationResponse;
import com.fashion.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

import static com.fashion.demo.Enum.RoleName.ROLE_USER;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginReqDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegRequestDTO signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new CommonResponseDTO(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new CommonResponseDTO(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserEntity user = new UserEntity(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
        String role = "ROLE_USER";
        Optional<RoleEntity> userRole = roleRepository.findByName(role);
//                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole.get()));

        UserEntity result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new CommonResponseDTO(true, "User registered successfully"));
    }

}
