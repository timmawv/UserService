package avlyakulov.timur.userservice.repository;

import avlyakulov.timur.userservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        user = new User("dima@gmail.com", "Dima", "Bevj", LocalDate.of(2002, 9, 12), "c. Kiev", "+380501231234");
    }

    @Test
    void createUser_userWasCreated() {
        userRepository.createUser(user);

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
    void findAll_usersWereFound() {
        userRepository.createUser(user);

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
    }

    @Test
    void updateUser_userWasUpdated() {
        userRepository.createUser(user);
        User updatedUser = new User("dima@gmail.com", "Dima", "Benz", LocalDate.of(2000, 9, 12), "c. Kiev", "+380501231234");

        userRepository.updateUser("dima@gmail.com", updatedUser);
        User maybeUpdatedUser = userRepository.findAll().get(0);

        assertThat(maybeUpdatedUser.getEmail()).isEqualTo("dima@gmail.com");
        assertThat(maybeUpdatedUser.getFirstName()).isEqualTo("Dima");
        assertThat(maybeUpdatedUser.getLastName()).isEqualTo("Benz");
        assertThat(maybeUpdatedUser.getBirthDate()).isEqualTo(LocalDate.of(2000, 9, 12));
        assertThat(maybeUpdatedUser.getAddress()).isEqualTo("c. Kiev");
        assertThat(maybeUpdatedUser.getPhoneNumber()).isEqualTo("+380501231234");
    }

    @Test
    void deleteUser_userWasDeletedByHisEmail() {
        String emailUser = user.getEmail();
        userRepository.createUser(user);

        userRepository.deleteUser(emailUser);
        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(0);
    }
}