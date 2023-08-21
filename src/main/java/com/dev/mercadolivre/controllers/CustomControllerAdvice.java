package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.model.exceptions.ModelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.security.sasl.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map erros = new HashMap<String,String>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            erros.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<Map<String,String>>(erros, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map erros = new HashMap<String,String>();
        for(ConstraintViolation error : ex.getConstraintViolations()){
            erros.put(error.getPropertyPath().toString(), error.getMessage());
        }
        return new ResponseEntity<Map<String,String>>(erros, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> constraintIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map erros = new HashMap<String,String>();
        erros.put("mensagem", ex.getMessage());
        return new ResponseEntity<Map<String,String>>(erros, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModelException.class)
    public ResponseEntity<Map<String,String>> constrainModelException(ModelException ex, WebRequest request) {
        Map erros = new HashMap<String,String>();
        erros.put("mensagem", ex.getMessage());
        return new ResponseEntity<Map<String,String>>(erros, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String,String>> constrainAuthenticationException(AuthenticationException ex, WebRequest request) {
        Map erros = new HashMap<String,String>();
        erros.put("mensagem", ex.getMessage());
        return new ResponseEntity<Map<String,String>>(erros, HttpStatus.FORBIDDEN);
    }
}
