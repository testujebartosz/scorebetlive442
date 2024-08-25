package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.UserEntity;
import com.bart.scorebetlive442.mapper.UserMapper;
import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public User updateUserById(Long id, User user) {
        UserEntity userById = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        if (user.getUsername() != null) userById.setUsername(user.getUsername());
        if (user.getFirstName() != null) userById.setFirstName(user.getFirstName());
        if (user.getLastName() != null) userById.setLastName(user.getLastName());
        if (user.getEmail() != null) userById.setEmail(user.getEmail());
        if (user.getDateOfBirth() != null) userById.setDateOfBirth(user.getDateOfBirth());
        if (user.getCountry() != null) userById.setCountry(user.getCountry());
        if (user.getPassword() != null) userById.setPassword(user.getPassword());

        userRepository.save(userById);

        return userMapper.toUserModel(userById);
    }
}
