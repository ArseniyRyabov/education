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
    public String getUser() {
        return "Список пользователей";
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserCreatingRequest userCreatingRequest) {
        UserResponse userResponse = new UserResponse(userCreatingRequest.getName(), userCreatingRequest.getLastName(), userCreatingRequest.getSecondName());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/user_id")
    public ResponseEntity<Long> addUserId(@RequestBody UserCreatingRequest request) {
        Long userId = usersService.create(request);
        return ResponseEntity.ok(userId);
    }

}
