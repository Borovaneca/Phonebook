package phonebook.entity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidLoginValidator.class)
public @interface ValidLogin {

    String currentUsername();

    String currentPassword();

    String message() default "Wrong username or password!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
