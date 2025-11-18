package com.github.arseniyryabov.education.controller;

import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;
import com.github.arseniyryabov.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.arseniyryabov.education.controller.model.UserResponse;
import com.github.arseniyryabov.education.service.UsersService;

@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/user")
    public String getAll() {
        return "Список пользователей";
    }

    @PostMapping("/user")
    public ResponseEntity<Long> create(@RequestBody UserCreatingRequest userCreatingRequest) {
        Long userId = usersService.create(userCreatingRequest);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }



}
