package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.model.json.UserCreateJson;
import com.bart.scorebetlive442.model.json.UserResponseJson;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertJsonToUser(UserCreateJson userCreateJson) {
        User user = new User();
        user.setUsername(userCreateJson.username());
        user.setPassword(userCreateJson.password());
        user.setFirstName(userCreateJson.firstName());
        user.setLastName(userCreateJson.lastName());
        user.setEmail(userCreateJson.email());
        user.setDateOfBirth(userCreateJson.dateOfBirth());
        user.setCountry(userCreateJson.country());;
        return user;
    }

    public UserResponseJson convertUserToJson(User user) {
        return new UserResponseJson(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getCountry(),
                user.getId()
        );
    }
}
