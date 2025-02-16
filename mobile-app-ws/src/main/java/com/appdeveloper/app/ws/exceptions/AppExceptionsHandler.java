package com.appdeveloper.app.ws.exceptions;

import com.appdeveloper.app.ws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) {
            errorMessageDescription = ex.toString();
        }
        ErrorMessage message = new ErrorMessage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) {
            errorMessageDescription = ex.toString();
        }
        ErrorMessage message = new ErrorMessage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
/*
    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) {
            errorMessageDescription = ex.toString();
        }
        ErrorMessage message = new ErrorMessage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/
}
