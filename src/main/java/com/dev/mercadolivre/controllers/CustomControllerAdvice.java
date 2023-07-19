package com.dev.mercadolivre.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> resourceNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
        Map erros = new HashMap<String,String>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            erros.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<Map<String,String>>(erros, HttpStatus.BAD_REQUEST);
    }

}
