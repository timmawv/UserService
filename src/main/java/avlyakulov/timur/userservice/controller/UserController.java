package avlyakulov.timur.userservice.controller;

import avlyakulov.timur.userservice.dto.ApiMessageResponse;
import avlyakulov.timur.userservice.dto.BirthDateRange;
import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.dto.UserRequestUpdate;
import avlyakulov.timur.userservice.mapper.UserMapper;
import avlyakulov.timur.userservice.model.User;
import avlyakulov.timur.userservice.service.UserService;
import avlyakulov.timur.userservice.util.UserAgeValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private UserMapper userMapper;

    private UserAgeValidator userAgeValidator;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, UserAgeValidator userAgeValidator) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userAgeValidator = userAgeValidator;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest) {
        userAgeValidator.validateUserAge(userRequest);
        User user = userMapper.mapUserRequestToUser(userRequest);
        userService.createUser(user);
        return ResponseEntity.ok(userRequest);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers(@RequestBody(required = false) @Valid BirthDateRange birthDateRange) {
        if (birthDateRange == null)
            return ResponseEntity.ok(userService.findAll());

        return ResponseEntity.ok(userService.findUsersByRangeDates(birthDateRange));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok(new ApiMessageResponse("User was deleted"));
    }

    @PatchMapping("/{email}")
    public ResponseEntity<?> updateSomeUserFields(@RequestBody @Valid UserRequestUpdate userRequestUpdate, @PathVariable String email) {
        userAgeValidator.validateUserAge(userRequestUpdate);
        User updatedUser = userService.updateUser(email, userRequestUpdate);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<?> updateAllUserFields(@RequestBody @Valid UserRequest userRequest, @PathVariable String email) {
        userAgeValidator.validateUserAge(userRequest);
        User user = userMapper.mapUserRequestToUser(userRequest);
        User updatedUser = userService.updateAllUserFields(email, user);
        return ResponseEntity.ok(updatedUser);
    }
}