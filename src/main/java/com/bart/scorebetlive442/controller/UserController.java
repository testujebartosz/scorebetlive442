package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.UserMapper;
import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.model.json.UserCreateJson;
import com.bart.scorebetlive442.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<User> createUser(@RequestBody UserCreateJson userCreateJson) {
        User user = userMapper.convertJsonToUser(userCreateJson);
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        List<User> existingUsers = new ArrayList<>();

        if (username == null || username.isEmpty()) {
            existingUsers.addAll(userService.getAllUsers());
        } else {
            existingUsers.addAll(userService.getUserByUsername(username));
        }

        return new ResponseEntity<>(existingUsers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User userToBeRemoved = userService.remove(id);

        if (userToBeRemoved != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
