package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.mapper.UserMapper;
import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.model.json.UserCreateJson;
import com.bart.scorebetlive442.model.json.UserResponseJson;
import com.bart.scorebetlive442.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<UserResponseJson> createUser(@RequestBody UserCreateJson userCreateJson) {
        User user = userMapper.convertJsonToUser(userCreateJson);
        User registeredUser = userService.registerUser(user);
        UserResponseJson userResponse = userMapper.convertUserToJson(registeredUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseJson> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            UserResponseJson userResponse = userMapper.convertUserToJson(user);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseJson>> getAllUsers(@RequestParam(required = false) String username) {
        List<User> existingUsers = userService.getAllUsers();
        List<UserResponseJson> userResponses = existingUsers.stream()
                .filter(user -> username == null || username.isEmpty() || user.getUsername().equalsIgnoreCase(username))
                .map(userMapper::convertUserToJson)
                .collect(Collectors.toList());

        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isUserRemoved = userService.removeUserById(id);

        if (isUserRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<UserResponseJson> updateUser(@PathVariable Long id, @RequestBody UserCreateJson bodyUser) {
        User user = userMapper.convertJsonToUser(bodyUser);
        User updatedUser = userService.updateUserById(id, user);
        return new ResponseEntity<>(userMapper.convertUserToJson(updatedUser), HttpStatus.OK);
    }
}
