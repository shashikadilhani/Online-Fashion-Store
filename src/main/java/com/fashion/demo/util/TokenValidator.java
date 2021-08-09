package com.fashion.demo.util;


import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.fashion.demo.constant.ApplicationConstant.RESOURCE_NOT_FOUND;


@Component
public class TokenValidator {

    private static final Logger LOGGER = LogManager.getLogger(com.fashion.demo.util.TokenValidator.class);

    private final UserService userService;

    @Autowired
    public TokenValidator(UserService userService) {
        this.userService = userService;
    }

    public UserEntity retrieveUserInformationFromAuthentication() {
        LOGGER.info("Execute method retrieveUserInformationFromAuthentication : @param : no");
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                return userService.findUserForValidation(authentication.getName());
            }
            throw new ServiceException(RESOURCE_NOT_FOUND, "Can't find user details from token");
        } catch (Exception e) {
            LOGGER.error("Method retrieveUserInformationFromAuthentication : " + e.getMessage());
            throw e;
        }
    }

//    public AdminUserEntity retrieveAdminUserInformationFromAuthentication() {
//        LOGGER.info("Execute method retrieveAdminUserInformationFromAuthentication : @param : no");
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (!(authentication instanceof AnonymousAuthenticationToken)) {
//                return userService.findAdminUserForMobile(authentication.getName());
//            }
//            throw new ServiceException(RESOURCE_NOT_FOUND, "Can't find user details from token");
//        } catch (Exception e) {
//            LOGGER.error("Method retrieveUserInformationFromAuthentication : " + e.getMessage());
//            throw e;
//        }
//    }
}
