package avlyakulov.timur.userservice.service.impl;

import avlyakulov.timur.userservice.dto.BirthDateRange;
import avlyakulov.timur.userservice.dto.UserRequestUpdate;
import avlyakulov.timur.userservice.exception.UserEmailAlreadyExistException;
import avlyakulov.timur.userservice.exception.UserNotFoundException;
import avlyakulov.timur.userservice.model.User;
import avlyakulov.timur.userservice.repository.UserRepository;
import avlyakulov.timur.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public User createUser(User user) {
        try {
            userRepository.findByEmail(user.getEmail());
            throw new UserEmailAlreadyExistException("user with such email already exists");
        } catch (UserNotFoundException e) {
            return userRepository.createUser(user);
        }
    }

    @Override
    public User updateUser(String email, UserRequestUpdate updatedUser) {
        User user = userRepository.findByEmail(email);

        if (updatedUser.getEmail() != null) {
            try {
                userRepository.findByEmail(updatedUser.getEmail());
                throw new UserEmailAlreadyExistException("User with such email already exist");
            } catch (UserNotFoundException e) {
                user.setEmail(updatedUser.getEmail());
            }
        }

        if (updatedUser.getFirstName() != null) {
            user.setFirstName(updatedUser.getFirstName());
        }

        if (updatedUser.getLastName() != null) {
            user.setLastName(updatedUser.getLastName());
        }

        if (updatedUser.getBirthDate() != null) {
            user.setBirthDate(updatedUser.getBirthDate());
        }

        if (updatedUser.getAddress() != null) {
            user.setAddress(updatedUser.getAddress());
        }

        if (updatedUser.getPhoneNumber() != null) {
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        return userRepository.updateUser(email, user);
    }

    @Override
    public User updateAllUserFields(String email, User updatedUser) {
        userRepository.findByEmail(email);

        return userRepository.updateUser(email, updatedUser);
    }

    @Override
    public void deleteUser(String email) {
        userRepository.findByEmail(email);

        userRepository.deleteUser(email);
    }

    @Override
    public List<User> findUsersByRangeDates(BirthDateRange birthDateRange) {
        LocalDate from = birthDateRange.getFrom();
        LocalDate to = birthDateRange.getTo();
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(u -> u.getBirthDate().isAfter(from) && u.getBirthDate().isBefore(to))
                .toList();
    }
}