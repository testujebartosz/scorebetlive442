package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.UserEntity;
import com.bart.scorebetlive442.mapper.UserMapper;
import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User registerUser(User user) {
        UserEntity toSave = userMapper.toEntity(user);
        UserEntity saved = userRepository.save(toSave);

        return userMapper.toUserModel(saved);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserModel)
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserModel)
                .collect(Collectors.toList());
    }

    public boolean removeUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
