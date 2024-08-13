package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<Long, User> DATA = new HashMap<>();
    private Long currentUserId = 1L;

    public User registerUser(User user) {

        if (user.getPassword().length() < 5) {
            throw new RuntimeException("Za krotkie haslo");
        }

        user.setId(currentUserId++);
        DATA.put(user.getId(), user);
        return user;
    }

    public User getUserById(Long id) {
        return DATA.get(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(DATA.values());
    }

    public User remove(Long id) {
        return DATA.remove(id);
    }

    public User updateUserById(Long id, User user) {
        var userById = getUserById(id);

        userById.setCountry(user.getCountry());
        if (user.getPassword() != null) {
            userById.setPassword(user.getPassword());
        }

//        DATA.put(user.getId(), userById);

        return userById;
    }
}
