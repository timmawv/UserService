package avlyakulov.timur.userservice.util;

import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.dto.UserRequestUpdate;
import avlyakulov.timur.userservice.exception.UserAgeNotValidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class UserAgeValidator {

    @Value("${user.register.age}")
    private int minUserAgeRegister;

    public void validateUserAge(UserRequest user) {
        if (user.getBirthDate() != null) {
            LocalDate birthDate = user.getBirthDate();

            if (calculateAge(birthDate) < minUserAgeRegister) {
                throw new UserAgeNotValidException("You can't create/update user with age less than " + minUserAgeRegister);
            }
        }
    }

    public void validateUserAge(UserRequestUpdate user) {
        if (user.getBirthDate() != null) {
            LocalDate birthDate = user.getBirthDate();

            if (calculateAge(birthDate) < minUserAgeRegister) {
                throw new UserAgeNotValidException("You can't create/update user with age less than " + minUserAgeRegister);
            }
        }
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}