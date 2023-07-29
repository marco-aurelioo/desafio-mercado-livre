package com.dev.mercadolivre.model.validations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.dev.mercadolivre.controllers.model.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class CreateUserRequestValidationTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testContactSuccess() {

        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("marco_silva@gmail.com");
        request.setUsername("marcosilva");
        request.setPassword("marcosilva");

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty());

    }

    @Test
    public void testContactFail() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("marco_silva??gmail.com"); //nao deve passar email invalido
        request.setUsername("");//nao deve passar username invalido
        request.setPassword("12345");//nao deve passar senha invalida

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals(3, violations.size());

    }





}
