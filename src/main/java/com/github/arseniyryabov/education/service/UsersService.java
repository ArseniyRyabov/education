package com.github.arseniyryabov.education.service;

import com.github.arseniyryabov.education.controller.model.UserResponse;
import com.github.arseniyryabov.education.exceptions.UserNotFoundException;
import com.github.arseniyryabov.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.
stereotype.Service;
import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;

import java.util.List;
import java.util.Optional;

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

    public UserResponse getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return new UserResponse(userEntity.getId(), userEntity.getUserName(), userEntity.getLastName(), userEntity.getSecondName());
        }
        else {
            throw new UserNotFoundException("Пользователь с ID " + id + " не найден");
        }
    }
}
