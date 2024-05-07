package avlyakulov.timur.userservice.service.impl;

import avlyakulov.timur.userservice.dto.BirthDateRange;
import avlyakulov.timur.userservice.dto.UserRequestUpdate;
import avlyakulov.timur.userservice.exception.UserEmailAlreadyExistException;
import avlyakulov.timur.userservice.exception.UserNotFoundException;
import avlyakulov.timur.userservice.model.User;
import avlyakulov.timur.userservice.repository.UserRepository;
import avlyakulov.timur.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceImplTest {

    private UserService userService;

    private UserRepository userRepository;

    private User user = new User("dima@gmail.com", "Dima", "Bevj", LocalDate.of(2005, 3, 3), "c. Kiyiv", "+380123456789");

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser_userNotExist_userWasCreated() {
        userService.createUser(user);

        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(1);

        User maybeUser = users.get(0);
        assertThat(maybeUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(maybeUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(maybeUser.getLastName()).isEqualTo(user.getLastName());
        assertThat(maybeUser.getBirthDate()).isEqualTo(user.getBirthDate());
        assertThat(maybeUser.getAddress()).isEqualTo(user.getAddress());
        assertThat(maybeUser.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @Test
    void createUser_userAlreadyExist_userNotCreated() {
        userService.createUser(user);

        assertThrows(UserEmailAlreadyExistException.class, () -> userService.createUser(user));

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
    }

    @Test
    void updateUser_userAlreadyExist_userWasUpdated() {
        userService.createUser(user);

        UserRequestUpdate updatedUser = new UserRequestUpdate();
        updatedUser.setFirstName("Jenya");
        updatedUser.setAddress("country Ukraine, city Lviv");

        userService.updateUser(user.getEmail(), updatedUser);

        User userUpdated = userRepository.findAll().get(0);
        assertThat(userUpdated.getEmail()).isEqualTo(user.getEmail());
        assertThat(userUpdated.getFirstName()).isEqualTo(updatedUser.getFirstName());
        assertThat(userUpdated.getLastName()).isEqualTo(user.getLastName());
        assertThat(userUpdated.getBirthDate()).isEqualTo(user.getBirthDate());
        assertThat(userUpdated.getAddress()).isEqualTo(updatedUser.getAddress());
        assertThat(userUpdated.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @Test
    void updateUser_userNotExist_userNotUpdated() {
        userService.createUser(user);

        String emailNotValid = "invalid@gmail.com";
        UserRequestUpdate updatedUser = new UserRequestUpdate();
        updatedUser.setFirstName("Jenya");
        updatedUser.setAddress("country Ukraine, city Lviv");

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(emailNotValid, updatedUser));
    }

    @Test
    void updateAllUserFields_userAlreadyExist_userWasUpdated() {
        userService.createUser(user);

        User updatedUser = new User("dima@gmail.com", "Dim4ik", "Lopin", LocalDate.of(2005, 4, 4), "c. Australia", null);
        updatedUser.setFirstName("Jenya");
        updatedUser.setAddress("country Ukraine, city Lviv");

        userService.updateAllUserFields(user.getEmail(), updatedUser);

        User userUpdated = userRepository.findAll().get(0);
        assertThat(userUpdated.getEmail()).isEqualTo(updatedUser.getEmail());
        assertThat(userUpdated.getFirstName()).isEqualTo(updatedUser.getFirstName());
        assertThat(userUpdated.getLastName()).isEqualTo(updatedUser.getLastName());
        assertThat(userUpdated.getBirthDate()).isEqualTo(updatedUser.getBirthDate());
        assertThat(userUpdated.getAddress()).isEqualTo(updatedUser.getAddress());
        assertThat(userUpdated.getPhoneNumber()).isEqualTo(updatedUser.getPhoneNumber());
    }

    @Test
    void updateAllUserFields_userNotExist_userNotUpdated() {
        userService.createUser(user);

        String emailNotValid = "invalid@gmail.com";
        User updatedUser = new User("dima@gmail.com", "Dim4ik", "Lopin", LocalDate.of(2005, 4, 4), "c. Australia", null);

        assertThrows(UserNotFoundException.class, () -> userService.updateAllUserFields(emailNotValid, updatedUser));
    }

    @Test
    void deleteUser_userWasDeleted_userExist() {
        userService.createUser(user);

        userService.deleteUser(user.getEmail());

        List<User> users = userService.findAll();
        assertThat(users).hasSize(0);
    }

    @Test
    void deleteUser_userNotDeleted_userNotExist() {
        userService.createUser(user);

        String invalidEmail = "dummy@gmail.com";
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(invalidEmail));
    }

    @Test
    void findUsersByRangeDates_userWasFoundInRange() {
        userService.createUser(user);
        BirthDateRange birthDateRange = new BirthDateRange(LocalDate.of(2002, 3, 3), LocalDate.of(2024, 3, 3));

        List<User> users = userService.findUsersByRangeDates(birthDateRange);
        assertThat(users).hasSize(1);
    }

    @Test
    void findUsersByRangeDates_userNotFoundInRange() {
        userService.createUser(user);
        BirthDateRange birthDateRange = new BirthDateRange(LocalDate.of(2002, 3, 3), LocalDate.of(2003, 3, 3));

        List<User> users = userService.findUsersByRangeDates(birthDateRange);
        assertThat(users).hasSize(0);
    }
}