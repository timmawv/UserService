package avlyakulov.timur.userservice.mapper;

import avlyakulov.timur.userservice.dto.UserRequest;
import avlyakulov.timur.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User mapUserRequestToUser(UserRequest userRequest);
}