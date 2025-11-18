package com.github.arseniyryabov.education.service;

import com.github.arseniyryabov.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;

    public Long create(UserCreatingRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLastName(request.getLastName());
        userEntity.setUserName(request.getUserName());
        userEntity.setSecondName(request.getSecondName());
        UserEntity savedUser = userRepository.save(userEntity);
        return savedUser.getId();
    }
}
