package avlyakulov.timur.userservice.service.impl;

import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> findAll();

    void createUser(User user);

    void updateUser(Integer id, UserRequest userRequest);

    void deleteUser(Integer id);

    List<User> findUsersByRangeDates(LocalDate from, LocalDate to);
}