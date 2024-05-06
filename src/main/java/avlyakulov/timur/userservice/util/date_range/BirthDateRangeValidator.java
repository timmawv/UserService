package avlyakulov.timur.userservice.util.date_range;

import avlyakulov.timur.userservice.dto.BirthDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BirthDateRangeValidator implements ConstraintValidator<BirthDateRangeValidation, Object> {

    @Override
    public void initialize(BirthDateRangeValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BirthDateRange birthDateRange = (BirthDateRange) o;
        return birthDateRange.getFrom().isBefore(birthDateRange.getTo());
    }
}
