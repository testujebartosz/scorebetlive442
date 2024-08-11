package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Map<Long, User> DATA = new HashMap<>();
    private Long currentUserId = 1L;

    public User registerUser(User user) {
        user.setId(currentUserId++);
        DATA.put(user.getId(), user);
        return user;
    }

    public User getUserById(Long id) {
        return DATA.get(id);
    }

    public List<User> getUserByUsername(String username) {
        return DATA.values()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(DATA.values());
    }

    public User remove(Long id) {
        return DATA.remove(id);
    }
}
