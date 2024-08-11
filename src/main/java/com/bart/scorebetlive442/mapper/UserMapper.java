package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.model.json.UserCreateJson;
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
}
