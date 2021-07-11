package com.shoppingcart.demo.service.Impl;

import com.shoppingcart.demo.Entity.UserEntity;
import com.shoppingcart.demo.Exception.ServiceException;
import com.shoppingcart.demo.Repository.UserRepository;
import com.shoppingcart.demo.Security.UserPrincipal;
import com.shoppingcart.demo.service.UserService;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.shoppingcart.demo.constant.ApplicationConstant.RESOURCE_NOT_FOUND;

@Service(value = "userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // Let people login with either username or email
        UserEntity user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                );

        return com.shoppingcart.demo.Security.UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ServiceException(RESOURCE_NOT_FOUND,"user not found")
        );

        return UserPrincipal.create(user);
    }
}
