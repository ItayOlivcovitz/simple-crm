package com.simplecrm.users.mapper;


import com.simplecrm.users.dto.UserDto;
import com.simplecrm.users.entity.Role;
import com.simplecrm.users.entity.User;


import java.util.stream.Collectors;

public class UserMapper {

    // Populates an existing UsersDto object from a Users entity
    public static UserDto MapToDto(User user, UserDto userDto) {
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());

        // Populate roles if they exist
        if (user.getRoles() != null) {
            userDto.setRoles(user.getRoles().stream()
                    .map(Role::getName) // Extract role name
                    .collect(Collectors.toSet()));
        } else {
            userDto.setRoles(null);
        }

        return userDto;
    }


    // Populates an existing Users entity from a UsersDto object
    public static User MapToUser(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());

        System.out.println("userDto FirstName - : " + userDto.getFirstName());
        System.out.println("userDto LastName - : " + userDto.getLastName());
        // Set roles to null or convert from role names if necessary
        // Here we assume roles will be set in the service layer if needed

        return user;
    }
}
