package avlyakulov.timur.userservice.controller;

import avlyakulov.timur.userservice.dto.*;
import avlyakulov.timur.userservice.mapper.UserMapper;
import avlyakulov.timur.userservice.model.User;
import avlyakulov.timur.userservice.service.UserService;
import avlyakulov.timur.userservice.util.UserAgeValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final UserAgeValidator userAgeValidator;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        userAgeValidator.validateUserAge(userRequest);
        User user = userMapper.mapUserRequestToUser(userRequest);
        User userCreated = userService.createUser(user);
        return ResponseEntity.ok(userMapper.mapUserToUserResponse(userCreated));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers(@RequestBody(required = false) @Valid BirthDateRange birthDateRange) {
        if (birthDateRange == null) {
            List<User> users = userService.findAll();
            return ResponseEntity.ok(userMapper.mapUserListToUserResponseList(users));
        }

        List<User> users = userService.findUsersByRangeDates(birthDateRange);
        return new ResponseEntity<>(userMapper.mapUserListToUserResponseList(users), HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<ApiMessageResponse> deleteUser(@PathVariable("email") String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok(new ApiMessageResponse("User was deleted"));
    }

    @PatchMapping("/{email}")
    public ResponseEntity<UserResponse> updateSomeUserFields(@RequestBody @Valid UserRequestUpdate userRequestUpdate, @PathVariable("email") String email) {
        userAgeValidator.validateUserAge(userRequestUpdate);
        User updatedUser = userService.updateUser(email, userRequestUpdate);
        return ResponseEntity.ok(userMapper.mapUserToUserResponse(updatedUser));
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<UserResponse> updateAllUserFields(@RequestBody @Valid UserRequest userRequest, @PathVariable("email") String email) {
        userAgeValidator.validateUserAge(userRequest);
        User user = userMapper.mapUserRequestToUser(userRequest);
        User updatedUser = userService.updateAllUserFields(email, user);
        return ResponseEntity.ok(userMapper.mapUserToUserResponse(updatedUser));
    }
}