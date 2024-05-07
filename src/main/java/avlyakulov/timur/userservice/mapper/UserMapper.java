package avlyakulov.timur.userservice.mapper;

import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.dto.UserResponse;
import avlyakulov.timur.userservice.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface UserMapper {
    User mapUserRequestToUser(UserRequest userRequest);

    UserResponse mapUserToUserResponse(User user);

    @AfterMapping
    default void convertBirthDate(User user, @MappingTarget UserResponse userResponse) {
        LocalDate birthDate = user.getBirthDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        userResponse.setBirthDate(birthDate.format(dateTimeFormatter));
    }

    List<UserResponse> mapUserListToUserResponseList(List<User> users);
}