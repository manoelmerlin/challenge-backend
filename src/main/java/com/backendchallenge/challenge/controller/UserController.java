package com.backendchallenge.challenge.controller;

import com.backendchallenge.challenge.dto.UserDto;
import com.backendchallenge.challenge.model.User;
import com.backendchallenge.challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody UserDto userDto) {
        User user = userService.saveUser(userDto);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }
}
