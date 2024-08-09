package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<Integer, User> DATA = new HashMap<>();
    private int currentUserId = 1;

    public User addUser(User user) {
        user.setId(currentUserId++);
        DATA.put(user.getId(), user);
        return user;
    }
}
