package avlyakulov.timur.userservice.util.date_range;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = BirthDateRangeValidator.class)
@Documented
public @interface BirthDateRangeValidation {
    String message() default "Your birthdate range not correct. From has to be before to";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}