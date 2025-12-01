package com.github.arseniyryabov.education.controller;

import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;
import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.arseniyryabov.education.controller.model.UserResponse;
import com.github.arseniyryabov.education.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        UserEntity userEntity = usersService.getById(id);
        UserResponse userResponse = new UserResponse(userEntity.getId(), userEntity.getUserName(), userEntity.getLastName(), userEntity.getSecondName());
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserCreatingRequest userCreatingRequest) {
        Long userId = usersService.create(userCreatingRequest);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getSortingAndPagination(
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        List<UserEntity> users;
        if (lastName == null || lastName.isEmpty()) {
            users = userRepository.findAll(PageRequest.of(offset / limit, limit)).getContent();
        } else {
            users = userRepository.findWithSortingAndPagination(lastName, limit, offset);
        }
        List<UserResponse> userResponses = users.stream()
                .map(user -> new UserResponse(user.getId(), user.getUserName(), user.getLastName(), user.getSecondName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }
}
