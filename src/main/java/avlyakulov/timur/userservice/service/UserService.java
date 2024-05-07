package avlyakulov.timur.userservice.service;

import avlyakulov.timur.userservice.dto.BirthDateRange;
import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.dto.UserRequestUpdate;
import avlyakulov.timur.userservice.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User createUser(User user);

    User updateUser(String email, UserRequestUpdate userUpdated);

    User updateAllUserFields(String email, User user);

    void deleteUser(String email);

    List<User> findUsersByRangeDates(BirthDateRange birthDateRange);
}