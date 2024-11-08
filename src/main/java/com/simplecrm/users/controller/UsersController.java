package com.simplecrm.users.controller;

import com.simplecrm.users.constants.UserConstants;
import com.simplecrm.users.dto.ErrorResponseDto;
import com.simplecrm.users.dto.ResponseDto;
import com.simplecrm.users.dto.UserDto;
import com.simplecrm.users.service.IUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Users in SimpleCrm",
        description = "CRUD REST APIs in SimpleCrm to CREATE, UPDATE, FETCH AND DELETE User details"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class UsersController {
    private final IUsersService usersService;

    @Operation(
            summary = "Create User REST API",
            description = "REST API to create a new user in SimpleCrm"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto userDto) {
        usersService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Fetch User Details REST API",
            description = "REST API to fetch user details based on email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<UserDto> fetchUser(@Valid @RequestParam
                                             @Email(message = "Email should be valid") String email) {
        UserDto userDto = usersService.fetchUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(
            summary = "Update User Details REST API",
            description = "REST API to update user details based on email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UserDto userDto) {
        boolean isUpdated = usersService.updateUser(userDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete User Details REST API",
            description = "REST API to delete user details based on email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(@Valid @RequestParam
                                                  @Email(message = "Email should be valid") String email) {
        boolean isDeleted = usersService.deleteUser(email);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417_DELETE));
        }
    }
}
