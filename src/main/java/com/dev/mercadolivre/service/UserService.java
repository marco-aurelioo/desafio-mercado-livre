package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.UserModel;
import com.dev.mercadolivre.repository.entity.UserEntity;
import com.dev.mercadolivre.repository.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(@Valid UserModel user){
        cantSaveUserWithDuplicateEmail(user.getEmail());
        userRepository.save(new UserEntity(user));
    }

    private void cantSaveUserWithDuplicateEmail(String email){
        userRepository.findByEmail(email).ifPresent(u -> {
            throw new RuntimeException("User already exists");
        });
    }

}
