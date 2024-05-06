package avlyakulov.timur.userservice.controller;

import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.mapper.UserMapper;
import avlyakulov.timur.userservice.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userRequest);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}