package com.fashion.demo.util;

import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserControllerAuthenticator {

    private static final Logger LOGGER = LogManager.getLogger(com.fashion.demo.util.UserControllerAuthenticator.class);
    private final TokenValidator tokenValidator;

    @Autowired
    public UserControllerAuthenticator(TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    public void validateUser(long user_id){
        try{
            LOGGER.info("Authenticate request validateUserUpdate");
            UserEntity userEntity = tokenValidator.retrieveUserInformationFromAuthentication();

            if (userEntity.getId() != user_id){
                throw new ServiceException(403,"Denied to access another user");
            }

        }catch (Exception e){
            LOGGER.error("Request validateUserUpdate : "+e.getMessage());
            throw e;
        }
    }
}
