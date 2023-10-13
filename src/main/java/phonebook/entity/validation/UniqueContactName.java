package phonebook.entity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = UniqueContactNameValidator.class)
public @interface UniqueContactName {
    String message() default "You have already a contact with the same name!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
