package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.model.json.UserCreateJson;
import com.bart.scorebetlive442.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    private final UserService userService;
    private int currentUserId = 1;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public ResponseEntity<User> createUser(@RequestBody UserCreateJson user) {
        User u = mapFromUserCreateJson(user);
        User savedUser = userService.addUser(u);

        // USerResponseJson response = mapFromUserToUserJson(savedUser)

        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = users.get(id);
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
            existingUsers.addAll(users.values());
        } else {
            for (User user : users.values()) {
                if (user.getUsername().equals(username)) {
                    existingUsers.add(user);
                }
            }
        }

        return new ResponseEntity<>(existingUsers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        User userToBeRemoved = users.remove(id);

        if (userToBeRemoved != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private User mapFromUserCreateJson(UserCreateJson userCreateJson) {
        User user = new User();
        user.setUsername(userCreateJson.username());
        user.setPassword(userCreateJson.password());
        user.setEmail(userCreateJson.email());
        user.setCountry(userCreateJson.country());;
        return user;
    }
}
