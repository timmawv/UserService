package avlyakulov.timur.userservice.repository;

import avlyakulov.timur.userservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public void createUser(User user) {
        users.add(user);
    }
}