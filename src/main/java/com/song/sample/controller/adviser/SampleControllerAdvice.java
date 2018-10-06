package com.song.sample.controller.adviser;


import com.song.sample.exception.TestException;
import com.song.sample.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class SampleControllerAdvice {

    @ExceptionHandler(value = {TestException.class})
    @ResponseBody
    protected ResponseEntity testException(RuntimeException ex, HttpServletRequest httpServletRequest) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURL() + " " + ex.getMessage());
        errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    protected ResponseEntity exception(RuntimeException ex, HttpServletRequest httpServletRequest) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURL() + " " + ex.getMessage());
        errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
