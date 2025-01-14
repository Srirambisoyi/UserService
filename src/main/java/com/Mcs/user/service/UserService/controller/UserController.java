package com.Mcs.user.service.UserService.controller;

import com.Mcs.user.service.UserService.entities.User;
import com.Mcs.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //get single user using id
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUser(@PathVariable String userid)
    {
        User user=userService.getUser(userid);
        return ResponseEntity.ok(user);
    }

    //get All useer
    @GetMapping
    public ResponseEntity<List<User>> getAlluser()
    {
        List<User> alluser=userService.getAllUser();
        return ResponseEntity.ok(alluser);
    }
}
