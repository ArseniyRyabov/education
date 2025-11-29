package com.github.arseniyryabov.education.controller;

import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;
import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.arseniyryabov.education.controller.model.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = usersService.getUserById(id);
        if (userResponse != null) {
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserCreatingRequest userCreatingRequest) {
        Long userId = usersService.create(userCreatingRequest);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
}
