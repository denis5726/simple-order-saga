package com.example.simplesaga.user.mapper;

import com.example.simplesaga.user.dto.SignUpDto;
import com.example.simplesaga.user.dto.UserDto;
import com.example.simplesaga.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User fromSignUpDto(SignUpDto signUpDto);
}
