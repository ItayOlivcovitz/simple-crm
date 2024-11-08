package com.simplecrm.users.service.impl;

import com.simplecrm.users.dto.UserDto;
import com.simplecrm.users.entity.User;

import com.simplecrm.users.exception.ResourceNotFoundException;
import com.simplecrm.users.exception.UserAlreadyExistsException;
import com.simplecrm.users.mapper.UserMapper;
import com.simplecrm.users.repository.UsersRepository;
import com.simplecrm.users.service.IUsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements IUsersService {
    private UsersRepository usersRepository;

    /**
     * This method creates a new user account.
     *
     * @param userDto the user data transfer object containing user details
     */
    @Override
    public void createUser(UserDto userDto) {
        // Map UserDto to User entity
        User user = UserMapper.MapToUser(userDto, new User());

        // Check if a user with the given email already exists
        Optional<User> optionalUser = usersRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with the given email: " + userDto.getEmail());
        }

        // Save the new user
        usersRepository.save(user);
    }


    private User createNewUser(String email) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setCreatedBy("Anonymous");
        return newUser;
    }

    /**
     * Fetches the card for the given mobile number.
     *
     * @param email the email of the user
     * @return the User details
     */
    @Override
    public UserDto fetchUser(String email) {
        User user = usersRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("User", "email", email));
        return UserMapper.MapToDto(user, new UserDto());    }

    /**
     * Updates the card for the given card details
     *
     * @param usersDto the User details
     * @return true if the User was updated successfully, false otherwise
     */
    @Override
    public boolean updateUser(UserDto usersDto) {
        User users = usersRepository.findByEmail(usersDto.getEmail()).orElseThrow(
                ()-> new ResourceNotFoundException("User", "email", usersDto.getEmail())
        );
        UserMapper.MapToUser(usersDto, users);
        usersRepository.save(users);
        return true;
    }

    /**
     * Deletes the user for the given email.
     *
     * @param email the email of the user
     * @return true if the User was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteUser(String email) {
        User users = usersRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("user", "email", email)
        );
        usersRepository.deleteById(users.getEmail());
        return true;
    }
}
