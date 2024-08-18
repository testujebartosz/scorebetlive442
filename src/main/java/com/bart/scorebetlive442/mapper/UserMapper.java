package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.UserEntity;
import com.bart.scorebetlive442.model.User;
import com.bart.scorebetlive442.model.json.UserCreateJson;
import com.bart.scorebetlive442.model.json.UserResponseJson;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User convertJsonToUser(UserCreateJson userCreateJson);

    UserResponseJson convertUserToJson(User user);

    User toUserModel(UserEntity userEntity);

    UserEntity toEntity(User user);
}
