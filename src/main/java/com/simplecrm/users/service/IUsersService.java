package com.simplecrm.users.service;


import com.simplecrm.users.dto.UserDto;

public interface IUsersService {

    /**
     * Creates a user for the given email.
     *
     * @param userDto the email  of the user
     */
    void createUser(UserDto userDto);

    /**
     * Fetches the card for the given mobile number.
     *
     * @param email the email of the user
     * @return the User details
     */
    UserDto fetchUser(String email);

    /**
     * Updates the card for the given card details
     *
     * @param usersDto the User details
     * @return true if the User was updated successfully, false otherwise
     */
    boolean updateUser(UserDto usersDto);

    /**
     * Deletes the user for the given email.
     *
     * @param email the email of the user
     * @return true if the User was deleted successfully, false otherwise
     */
    boolean deleteUser(String email);
}
