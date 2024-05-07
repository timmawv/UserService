package avlyakulov.timur.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String email;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String address;

    private String phoneNumber;
}