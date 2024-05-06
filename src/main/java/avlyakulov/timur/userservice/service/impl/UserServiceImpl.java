package avlyakulov.timur.userservice.service.impl;

import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.model.User;
import avlyakulov.timur.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(Integer id, UserRequest userRequest) {

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public List<User> findUsersByRangeDates(LocalDate from, LocalDate to) {
        return null;
    }
}