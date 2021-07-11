package com.shoppingcart.demo.Exception;

import com.shoppingcart.demo.dto.response.ErrorMessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class use to catch all the exception which send to the controller layer
 * and return ResponseEntity with relevant messages to front-end
 */
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity handleServiceException(ServiceException ex){
        return new ResponseEntity<>(new ErrorMessageResponseDTO(ex.getStatus(),ex.getMessage()),HttpStatus.OK);
    }

}
