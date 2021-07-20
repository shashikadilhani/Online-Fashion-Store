package com.fashion.demo.service.Impl;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Enum.StockStatus;
import com.fashion.demo.dto.user.UserDTO;
import com.fashion.demo.dto.user.UserListDTO;
import com.fashion.demo.service.UserService;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.Repository.UserRepository;
import com.fashion.demo.Security.UserPrincipal;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fashion.demo.constant.ApplicationConstant.INPUT_NOT_FOUND;
import static com.fashion.demo.constant.ApplicationConstant.RESOURCE_NOT_FOUND;

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

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ServiceException(RESOURCE_NOT_FOUND,"user not found")
        );

        return UserPrincipal.create(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        LOGGER.info("Execute method getAllUsers ");
        try {
            List<UserEntity> userEntityList = userRepository.findAll();
            if(!userEntityList.isEmpty()){
                UserListDTO userListDTO = new UserListDTO();
                List<UserDTO> userDTOS = new ArrayList<>();

                for(UserEntity s: userEntityList){
                    UserDTO userDTO = new UserDTO();
                    userDTO.setName(s.getName());
                    userDTO.setEmail(s.getEmail());
                    userDTO.setId(s.getId());
                    userDTOS.add(userDTO);
                }
                userListDTO.setUsers(userDTOS);

                return userDTOS;

            } else{
                throw new ServiceException (INPUT_NOT_FOUND,"Input Not Found");
            }
        }catch (Exception e) {
            LOGGER.error("getAllUsers : " + e.getMessage(), e);
            throw e;
        }
    }
}
