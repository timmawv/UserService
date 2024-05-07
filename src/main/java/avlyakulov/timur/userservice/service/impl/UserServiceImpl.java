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
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent())
            throw new UserEmailAlreadyExistException("User with such email already exist");
        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(String email, UserRequestUpdate updatedUser) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty())
            throw new UserNotFoundException("User with such email doesn't exists");

        User user = userOptional.get();

        if (updatedUser.getEmail() != null) {
            Optional<User> updatedUserEmail = userRepository.findByEmail(updatedUser.getEmail());
            if (updatedUserEmail.isPresent())
                throw new UserEmailAlreadyExistException("User with such email already exist");
            user.setEmail(updatedUser.getEmail());
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
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty())
            throw new UserNotFoundException("User with such email doesn't exists");
        return userRepository.updateUser(email, updatedUser);
    }

    @Override
    public void deleteUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty())
            throw new UserNotFoundException("User with such email doesn't exists");
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