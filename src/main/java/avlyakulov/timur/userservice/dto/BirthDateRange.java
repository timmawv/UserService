package avlyakulov.timur.userservice.dto;

import avlyakulov.timur.userservice.util.date_range.BirthDateRangeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@BirthDateRangeValidation
public class BirthDateRange {

    @NotNull(message = "Birth date is required field, please enter birth date")
    @Past(message = "from must be in the past")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate from;

    @NotNull(message = "Birth date is required field, please enter birth date")
    @Past(message = "to must be in the past")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate to;
}