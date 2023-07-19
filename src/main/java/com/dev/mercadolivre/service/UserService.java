package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.UserModel;
import com.dev.mercadolivre.repository.entity.UserEntity;
import com.dev.mercadolivre.repository.entity.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void save(@Valid UserModel user){
        userRepository.save(new UserEntity(user));
    }


}
