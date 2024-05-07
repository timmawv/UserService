package avlyakulov.timur.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "Email is required field, please enter email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Your email isn't valid, please enter valid email")
    private String email;

    @NotNull(message = "First name is required field, please enter first name")
    @NotBlank(message = "Your name can't be blank enter valid name")
    @Size(min = 2, message = "Your first name is too short, enter valid first name")
    private String firstName;

    @NotNull(message = "Last name is required field, please enter last name")
    @NotBlank(message = "Your last name can't be blank enter valid last name")
    @Size(min = 2, message = "Your last name is too short, enter valid last name")
    private String lastName;

    @NotNull(message = "Birth date is required field, please enter birth date")
    @Past(message = "Birth date must be in the past")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    @Size(min = 4, message = "Your address is too short, enter valid address")
    private String address;

    @Pattern(regexp = "^\\+?3?8?(0\\d{9})$", message = "your phone number doesn't match +380 or 099 format")
    private String phoneNumber;
}