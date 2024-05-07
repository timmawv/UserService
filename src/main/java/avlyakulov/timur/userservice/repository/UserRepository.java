package avlyakulov.timur.userservice.repository;

import avlyakulov.timur.userservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }


    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public User updateUser(String email, User user) {
        int counter = 0;
        for (User userIterate : users) {
            if (userIterate.getEmail().equals(email))
                users.set(counter, user);
            ++counter;
        }
        return user;
    }

    public void deleteUser(String email) {
        users.removeIf(u -> u.getEmail().equals(email));
    }
}