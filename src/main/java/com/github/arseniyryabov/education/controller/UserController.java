package com.github.arseniyryabov.education.controller;
import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.arseniyryabov.education.controller.model.UserResponse;

@RestController
public class UserController {

    @GetMapping("/user")
    public String getUser() {
        return "Список пользователей";
    }

    @PostMapping("/user")
    public  ResponseEntity<UserResponse> addUser(@RequestBody UserCreatingRequest userCreatingRequest) {
        UserResponse userResponse = new UserResponse(userCreatingRequest.getName(), userCreatingRequest.getLastName(), userCreatingRequest.getSecondName());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

}
