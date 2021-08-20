package com.fashion.demo.service.Impl;

import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.dto.user.UserDTO;
import com.fashion.demo.dto.user.UserListDTO;
import com.fashion.demo.dto.user.UserViewDTO;
import com.fashion.demo.service.UserService;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.Repository.UserRepository;
import com.fashion.demo.Security.UserPrincipal;
import org.apache.log4j.LogManager;
import org.apache.tomcat.util.buf.UEncoder;
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
import java.util.Optional;

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

    @Override
    public UserEntity findUserForValidation(String userId) {
        try {
            LOGGER.info("Start findUserForMobile function  username: " + userId);

            UserEntity byEmail = userRepository.findByEmails(userId);
            if (byEmail != null) {
                return byEmail;
            }
            throw new ServiceException(RESOURCE_NOT_FOUND, "User not found");
        } catch (Exception e) {
            LOGGER.error("Function findUserForMobile  : " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserViewDTO viewUser(long user_id) {
        try {
            LOGGER.info("Find user by id: " + user_id);
            Optional<UserEntity> userEntity = userRepository.findById(user_id);
            if(userEntity.isPresent()){
                UserViewDTO userViewDTO = new UserViewDTO();
                UserEntity user = userEntity.get();
                userViewDTO.setBirthday(user.getBirthday());
                userViewDTO.setCreatedDate(user.getCreatedDate());
                userViewDTO.setEmail(user.getEmail());
                userViewDTO.setGender(user.getGender());
                userViewDTO.setName(user.getName());
                userViewDTO.setPhotoPath(user.getPhotoPath());
                userViewDTO.setId(user.getId());
                userViewDTO.setRole(user.getRole());
                userViewDTO.setStatus(user.getStatus());
                userViewDTO.setUsername(user.getUsername());
                return userViewDTO;
            }
            else{
                throw new ServiceException(RESOURCE_NOT_FOUND, "User not found");
            }
        } catch (Exception e) {
            LOGGER.error("Function findUserById : " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateProfile(long user_id, UserViewDTO userViewDTO) {
        try {
            LOGGER.info("Start Update User Profile Function: " + user_id);

            Optional<UserEntity> user = userRepository.findById(user_id);
            if(user.isPresent()){
                UserEntity entity = user.get();
                entity.setRole(userViewDTO.getRole());
                entity.setStatus(userViewDTO.getStatus());
                entity.setBirthday(userViewDTO.getBirthday());
                entity.setEmail(userViewDTO.getEmail());
                entity.setGender(userViewDTO.getGender());
                entity.setName(userViewDTO.getName());

                //image update
//                entity.setPhotoPath(i);

                //update role entity
//                entity.setRoles();

                userRepository.save(entity);
            }else{
                throw new ServiceException(RESOURCE_NOT_FOUND, "User not found");
            }

        } catch (Exception e) {
            LOGGER.error("Function findUserForMobile  : " + e.getMessage());
        }
    }
}
