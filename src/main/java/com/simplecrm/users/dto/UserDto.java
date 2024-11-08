package com.simplecrm.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(name = "UsersDto", description = "Schema to hold User information")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Schema(description = "User's email address", example = "user@example.com")
    private String email;

    @NotEmpty(message = "First name cannot be empty")
    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Schema(description = "User's last name", example = "Doe")
    private String lastName;



    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits")
    @Schema(description = "User's phone number", example = "1234567890")
    private String phoneNumber;

    @Schema(description = "Roles assigned to the user")
    private Set<String> roles; // Only role names to simplify the DTO
}
